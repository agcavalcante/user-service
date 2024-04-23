package com.gabriel.userservice.application.rest;

import com.gabriel.userservice.domain.command.CreateUser;
import com.gabriel.userservice.domain.commandHandler.CreateUserHandler;
import com.gabriel.userservice.domain.commandHandler.DeleteUserHandler;
import com.gabriel.userservice.domain.commandHandler.GetUserHandler;
import com.gabriel.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/private/user")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final CreateUserHandler createUserHandler;
    private final GetUserHandler getUserHandler;
    private final DeleteUserHandler deleteUserHandler;

    @PostMapping
    public Mono<User> createUser(@RequestBody CreateUser createUser) {
        return createUserHandler.handle(createUser);
    }

    @GetMapping(value = "/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return getUserHandler.handle(id);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return deleteUserHandler.handleSoftDelete(id);
    }
}
