package com.gabriel.userservice.domain.port.repository;

import com.gabriel.userservice.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<User> findById(String id);

    Mono<Void> delete(String id);
}
