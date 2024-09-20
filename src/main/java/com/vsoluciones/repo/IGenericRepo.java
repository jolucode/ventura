package com.vsoluciones.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;

@NoRepositoryBean
public interface IGenericRepo<T, ID> extends ReactiveMongoRepository<T, ID> {

    Flux<T> findAllBy(Pageable pageable);
}