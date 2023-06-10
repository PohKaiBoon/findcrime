package com.crimewatch.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.crimewatch.authenticationservice.dto.AuthRequest;
import com.crimewatch.authenticationservice.entity.User;
import com.crimewatch.authenticationservice.service.AuthService;

@RequestMapping("/auth")
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public String test(){
        return "Message sent!";
    }
    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        switch (authService.saveUser(user)) {
            case 200:
                return ResponseEntity.status(200).body("User registered successfully");
            case 409:
                return ResponseEntity.status(409).body("Username already exists");
            default:
                return ResponseEntity.status(404).body("Not found");
        }
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
