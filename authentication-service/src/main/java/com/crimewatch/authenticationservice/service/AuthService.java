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

    public int saveUser(User crendentials) {

        if (!checkIfUserExist(crendentials.getUsername())) {
            return 409;
        } else {
            crendentials.setPassword(passwordEncoder.encode(crendentials.getPassword()));
            repository.save(crendentials);
            return 200;
        }
    }

    public String generateJWTtoken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateJWTtoken(String token) {
        jwtService.validateToken(token);
    }

    public boolean checkIfUserExist(String username) {
        System.out.println("New user: " + username);
        System.out.println(repository.findByUsername(username).isEmpty());
        return repository.findByUsername(username).isEmpty();
        // return repository.findByUsername(username) != null ? true : false;
    }

}
