package com.vsoluciones.controller;

import com.vsoluciones.dto.LogDTO;
import com.vsoluciones.model.Log;
import com.vsoluciones.service.ILogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

import java.net.URI;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LogController {

    Logger logger = LoggerFactory.getLogger(LogController.class);


    private final ILogService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping
    public Mono<ResponseEntity<Flux<LogDTO>>> findAll() {
        Flux<LogDTO> fx = service.findAll()
                .map(this::convertToDto);
        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx))
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping("/paged")
    public Mono<ResponseEntity<Flux<LogDTO>>> findAllWithPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        // Capturar el tiempo de inicio
        long startTime = System.currentTimeMillis();

        // Loguear información sobre la petición recibida
        logger.info("Endpoint '/paged' invocado con los siguientes parámetros: page = {}, size = {}", page, size);

        Flux<LogDTO> fx = service.findAllWithPage(PageRequest.of(page, size))
                .map(this::convertToDto);

        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx))
                .doOnSuccess(response -> {
                    // Calcular el tiempo de procesamiento
                    long duration = System.currentTimeMillis() - startTime;
                    logger.info("Respuesta enviada con status: {}. Tiempo de procesamiento: {} ms", response.getStatusCode(), duration);
                })
                .doOnError(error -> {
                    // Calcular el tiempo de procesamiento en caso de error
                    long duration = System.currentTimeMillis() - startTime;
                    logger.error("Error al procesar la solicitud después de {} ms: ", duration, error);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping("/pagedByFilter")
    public Mono<ResponseEntity<Flux<LogDTO>>> findbyFilter(
            @RequestParam(value = "filter", defaultValue = "") String filter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        // Capturar el tiempo de inicio
        long startTime = System.currentTimeMillis();

        // Loguear información sobre la petición recibida
        logger.info("Endpoint '/paged' invocado con los siguientes parámetros: page = {}, size = {}", page, size);

        Flux<LogDTO> fx = service.findByFilter(filter,PageRequest.of(page, size))
                .map(this::convertToDto);

        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx))
                .doOnSuccess(response -> {
                    // Calcular el tiempo de procesamiento
                    long duration = System.currentTimeMillis() - startTime;
                    logger.info("Respuesta enviada con status: {}. Tiempo de procesamiento: {} ms", response.getStatusCode(), duration);
                })
                .doOnError(error -> {
                    // Calcular el tiempo de procesamiento en caso de error
                    long duration = System.currentTimeMillis() - startTime;
                    logger.error("Error al procesar la solicitud después de {} ms: ", duration, error);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<LogDTO>> findById(@PathVariable("id") String id) {
        return service.findById(id)
                .map(this::convertToDto)
                .map(x -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(x))
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<LogDTO>> save(@Valid @RequestBody LogDTO dto, final ServerHttpRequest req) {
        return service.save(convertToEntity(dto))
                .map(this::convertToDto)
                .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(x))
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<LogDTO>> update(@Valid @RequestBody LogDTO dto, @PathVariable("id") String id) {
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
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
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
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping("/countAll")
    public Mono<ResponseEntity<?>> countAll() {
        return service.countAll()
                .map(x -> {
                    return x;
                })
                .flatMap(x -> {
                    if (x.equals(0L)) {
                        return Mono.just(ResponseEntity.noContent().build());
                    }
                    else{
                        Map<String, Object> responseBody = new HashMap<>();
                        responseBody.put("count", x);
                        return Mono.just(ResponseEntity.ok(responseBody));
                    }
                })
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
                .defaultIfEmpty(ResponseEntity.notFound().build());  // Si el Mono está vacío, devolver 404
    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('SUPPORT')")
    @GetMapping("/countFilter")
    public Mono<ResponseEntity<?>> countFilter( @RequestParam(value = "filter", defaultValue = "") String filter) {
        return service.countFilter(filter)
                .map(x -> {
                    return x;
                })
                .flatMap(x -> {
                    if (x.equals(0L)) {
                        return Mono.just(ResponseEntity.noContent().build());
                    }
                    else{
                        Map<String, Object> responseBody = new HashMap<>();
                        responseBody.put("count", x);
                        return Mono.just(ResponseEntity.ok(responseBody));
                    }
                })
                .doOnSuccess(response -> logger.info("Respuesta enviada con status: {}", response.getStatusCode()))
                .doOnError(error -> logger.error("Error al procesar la solicitud: ", error))
                .defaultIfEmpty(ResponseEntity.notFound().build());  // Si el Mono está vacío, devolver 404
    }

    private LogDTO convertToDto(Log model) {
        return mapper.map(model, LogDTO.class);
    }

    private Log convertToEntity(LogDTO dto) {
        return mapper.map(dto, Log.class);
    }

}
