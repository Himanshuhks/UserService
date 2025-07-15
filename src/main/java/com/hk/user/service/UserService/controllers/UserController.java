package com.hk.user.service.UserService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/greet")
    public String greetings() {
        return "Hello, World !!";
    }
}
