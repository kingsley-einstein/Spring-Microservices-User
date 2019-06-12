package com.spring.microservices.controller;

import java.util.UUID;

import com.spring.microservices.client.ProfileClient;
import com.spring.microservices.client.model.Profile;
import com.spring.microservices.exceptions.IncorrectPasswordException;
import com.spring.microservices.exceptions.UserNotFoundException;
import com.spring.microservices.model.User;
import com.spring.microservices.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ProfileClient client;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        User user = userRepo.findById(id)
        .get();

        return user;
    }

    @PostMapping("/auth/signup")
    public User create(@RequestBody User user) {
        User newUser = new User(user.getUsername(), encoder.encode(user.getPassword()), UUID.randomUUID().toString());

        return userRepo.save(newUser);
    }

    @PostMapping("/auth/signin")
    public User login(@RequestBody User body) {
        User user = userRepo.findByUsername(body.getUsername());
        if (user != null) {
            if (encoder.matches(body.getPassword(), user.getPassword())) {
                return user;
            }
            else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @GetMapping("/user")
    public Profile getProfile(@RequestParam("id") Long id) {
        User user = userRepo.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with specified id not found"));

        return client.profile(user.getId());
    }
}