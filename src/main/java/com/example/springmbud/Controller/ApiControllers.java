package com.example.springmbud.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiControllers {

    @GetMapping("/home")
    public String index() {
        return "Hello Welcome to Spring MBUD";
    }
}




