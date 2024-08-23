package com.vsoluciones.service.impl;

import com.vsoluciones.repo.IGenericRepo;
import com.vsoluciones.service.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public abstract class CRUDImpl<T, D> implements ICRUD<T, D> {

  protected abstract IGenericRepo<T, D> getRepo();

  @Override
  public Mono<T> save(T t) {
    return getRepo().save(t);
  }

  @Override
  public Mono<T> udpate(T t, D id) {
    return getRepo().findById(id)
        .flatMap(e -> getRepo().save(t));
  }

  @Override
  public Flux<T> findAll() {
    return getRepo().findAll();
  }

  @Override
  public Flux<T> findAllWithPage(Pageable pageable) {
    return getRepo().findAllBy(pageable);
  }

  @Override
  public Mono<T> findById(D id) {
    return getRepo().findById(id);
  }

  @Override
  public Mono<Boolean> delete(D id) {
    return getRepo().findById(id)
        .hasElement()
        .flatMap(result -> {
          if (result) {
            return getRepo().deleteById(id).thenReturn(result);
          } else {
            return Mono.just(!result);
          }
        });
  }
}
