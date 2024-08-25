package com.vsoluciones.controller;

import com.vsoluciones.dto.DishDTO;
import com.vsoluciones.model.Dish;
import com.vsoluciones.service.IDishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

  private final IDishService service;

  @Qualifier("defaultMapper")
  private final ModelMapper mapper;

  @GetMapping
  public Mono<ResponseEntity<Flux<DishDTO>>> findAll() {
    Flux<DishDTO> fx = service.findAll()
        .map(this::convertToDto);
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fx))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<DishDTO>> findById(@PathVariable("id") String id) {
    return service.findById(id)
        .map(this::convertToDto)
        .map(x -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<DishDTO>> save(@Valid @RequestBody DishDTO dto, final ServerHttpRequest req) {
    return service.save(convertToEntity(dto))
        .map(this::convertToDto)
        .map(x -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(x.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(x))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  @PutMapping("/{id}")
  public Mono<ResponseEntity<DishDTO>> update(@Valid @RequestBody DishDTO dto, @PathVariable("id") String id) {
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

  private DishDTO convertToDto(Dish model) {
    return mapper.map(model, DishDTO.class);
  }

  private Dish convertToEntity(DishDTO dto) {
    return mapper.map(dto, Dish.class);
  }

}
