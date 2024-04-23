package com.gabriel.userservice.domain.service;

import com.gabriel.userservice.domain.exception.DomainErrorCode;
import com.gabriel.userservice.domain.exception.UserNotFoundException;
import com.gabriel.userservice.domain.model.User;
import com.gabriel.userservice.domain.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        return validateSocialId(user)
                .flatMap(userRepository::save);
    }

    private Mono<User> validateSocialId(User user) {
        if (null == user.getSocialId()) {
            return Mono.error(new IllegalArgumentException("Invalid social ID"));
        }
        return Mono.just(user);
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new UserNotFoundException(DomainErrorCode.user_not_found, "User with id " + id + " not found")
                        )
                );
    }
}
