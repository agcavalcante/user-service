package com.gabriel.userservice.domain.commandHandler;

import com.gabriel.userservice.domain.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class DeleteUserHandler {

    private final UserRepository userRepository;

    public Mono<Void> handle(String id) {
        log.info("Request received to delete user with id {}", id);
        return userRepository.delete(id)
                .doOnSuccess(usr -> log.info("User with id {} has been deleted", id));
    }

    public Mono<Void> handleSoftDelete(String id) {
        log.info("Request received to soft delete user with id {}", id);
        return userRepository.findById(id)
                .map(usr -> usr.softDeleteUser(usr))
                .flatMap(userRepository::save)
                .then().log();
    }
}
