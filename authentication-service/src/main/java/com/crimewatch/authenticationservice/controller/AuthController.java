package com.crimewatch.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimewatch.authenticationservice.dto.AuthRequest;
import com.crimewatch.authenticationservice.entity.User;
import com.crimewatch.authenticationservice.service.AuthService;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return authService.saveUser(user);
    }

    @PostMapping("/generatetoken")
    public String getJWTtoken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return authService.generateJWTtoken(authRequest.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }

    }

    @GetMapping("/validatetoken")
    public String validateJWTtoken(@RequestParam("token") String token) {
        authService.validateJWTtoken(token);
        return "Token valid";

    }
}
