package com.crimewatch.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crimewatch.authenticationservice.entity.User;
import com.crimewatch.authenticationservice.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public String saveUser(User crendentials) {
        crendentials.setPassword(passwordEncoder.encode(crendentials.getPassword()));
        repository.save(crendentials);
        return "User successfully added to the system";
    }

    public String generateJWTtoken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateJWTtoken(String token) {
        jwtService.validateToken(token);
    }

}
