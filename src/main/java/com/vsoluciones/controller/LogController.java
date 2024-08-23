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

import java.net.URI;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LogController {

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
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @CrossOrigin(origins = "http://localhost:4200")
  @PreAuthorize("hasAuthority('SUPPORT')")
  @GetMapping("/paged")
  public Mono<ResponseEntity<Flux<LogDTO>>> findAllWithPague(
          @RequestParam(value = "page", defaultValue = "0") int page,
          @RequestParam(value = "size", defaultValue = "10") int size) {

    Flux<LogDTO> fx = service.findAllWithPage(PageRequest.of(page, size))
            .map(this::convertToDto);

    return Mono.just(ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fx))
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<LogDTO>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(this::convertToDto)
        .map(x -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<LogDTO>> save(@Valid @RequestBody LogDTO dto, final ServerHttpRequest req) {
    return service.save(convertToEntity(dto))
        .map(this::convertToDto)
        .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
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

  private LogDTO convertToDto(Log model) {
    return mapper.map(model, LogDTO.class);
  }

  private Log convertToEntity(LogDTO dto) {
    return mapper.map(dto, Log.class);
  }

}
