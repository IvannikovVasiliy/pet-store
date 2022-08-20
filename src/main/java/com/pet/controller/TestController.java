package com.pet.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {
    @GetMapping("all")
    public String all() {
        return "public API";
    }

    @GetMapping("user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        System.out.println("fuighdfhdvfjidvfidvfji");
        return "user API";
    }
}
