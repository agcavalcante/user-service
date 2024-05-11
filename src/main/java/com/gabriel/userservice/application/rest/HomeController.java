package com.gabriel.userservice.application.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping
    public String home() {
        return "Hello World";
    }
}
