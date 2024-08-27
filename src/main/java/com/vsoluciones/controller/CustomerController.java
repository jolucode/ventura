package com.vsoluciones.controller;

import com.vsoluciones.dto.CustomerDTO;
import com.vsoluciones.model.Customer;
import com.vsoluciones.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

  private final ICustomerService service;

  @Qualifier("defaultMapper")
  private final ModelMapper mapper;

  @CrossOrigin(origins = "http://localhost:4200")
  @PreAuthorize("hasAuthority('FINANCE')")
  @GetMapping
  public Mono<ResponseEntity<Flux<CustomerDTO>>> findAll() {
      // Capturar el tiempo de inicio
      long startTime = System.currentTimeMillis();

      // Loguear información sobre la petición recibida
      logger.info("Endpoint '/findAll' invocado \n");

      Flux<CustomerDTO> fx = service.findAll()
              .map(this::convertToDto);

      return Mono.just(ResponseEntity.ok()
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(fx))
              .doOnSuccess(response -> {
                  // Calcular el tiempo de procesamiento
                  long duration = System.currentTimeMillis() - startTime;
                  logger.info("Respuesta enviada con status: {}. Tiempo de procesamiento: {} ms \n", response.getStatusCode(), duration);
              })
              .doOnError(error -> {
                  // Calcular el tiempo de procesamiento en caso de error
                  long duration = System.currentTimeMillis() - startTime;
                  logger.error("Error al procesar la solicitud después de {} ms: \n", duration, error);
              })
              .defaultIfEmpty(ResponseEntity.notFound().build());
  }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('FINANCE')")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> findById(@PathVariable("id") String id) {
        // Capturar el tiempo de inicio
        long startTime = System.currentTimeMillis();

        // Loguear información sobre la petición recibida
        logger.info("Endpoint '/findById' invocado con id: {} \n", id);

        return service.findById(id)
                .map(this::convertToDto)
                .map(x -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(x))
                .doOnSuccess(response -> {
                    // Calcular el tiempo de procesamiento
                    long duration = System.currentTimeMillis() - startTime;
                    logger.info("Respuesta enviada con status: {}. Tiempo de procesamiento: {} ms \n", response.getStatusCode(), duration);
                })
                .doOnError(error -> {
                    // Calcular el tiempo de procesamiento en caso de error
                    long duration = System.currentTimeMillis() - startTime;
                    logger.error("Error al procesar la solicitud después de {} ms: \n", duration, error);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

  @PostMapping
  public Mono<ResponseEntity<CustomerDTO>> save(@Valid @RequestBody CustomerDTO dto, final ServerHttpRequest req) {
    return service.save(convertToEntity(dto))
        .map(this::convertToDto)
        .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @PutMapping("/{id}")
  public Mono<ResponseEntity<CustomerDTO>> update(@Valid @RequestBody CustomerDTO dto, @PathVariable("id") String id) {
    return Mono.just(dto)
        .map(x -> {
          x.setId(id);
          return x;
        })
        .flatMap(x -> service.udpate(convertToEntity(dto), id))
        .map(this::convertToDto)
        .map(e -> ResponseEntity.ok()
            .body(e)
        )
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Object>> eliminar(@PathVariable("id") String id) {
    return service.delete(id)
        .flatMap(result -> {
          if (result) {
            return Mono.just(ResponseEntity.noContent().build());
          }
          return Mono.just(ResponseEntity.notFound().build());
        })
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PreAuthorize("hasAuthority('FINANCE')")
  @PatchMapping("/{id}")
  public Mono<ResponseEntity<Object>> updateCustomer(@PathVariable("id") String id) {
    return service.findById(id)
        .flatMap(customer -> {
          customer.setStatus(!customer.isStatus());
          return service.udpate(customer, id);
        })
        .map(this::convertToDto)
        .map(customer -> ResponseEntity.noContent().build())
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  private CustomerDTO convertToDto(Customer model) {
    return mapper.map(model, CustomerDTO.class);
  }

  private Customer convertToEntity(CustomerDTO dto) {
    return mapper.map(dto, Customer.class);
  }

}
