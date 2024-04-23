package com.gabriel.userservice.domain.commandHandler;

import com.gabriel.userservice.domain.model.User;
import com.gabriel.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class GetUserHandler {

    private final UserService userService;

    public Mono<User> handle(String id) {
        return userService.findById(id);
    }
}
