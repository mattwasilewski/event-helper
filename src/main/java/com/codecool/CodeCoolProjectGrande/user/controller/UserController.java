package com.codecool.CodeCoolProjectGrande.user.controller;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
//        return userServiceImpl.getUsers();
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserByID(@PathVariable UUID userId) {
        return userRepository.findById(userId);
//            return userServiceImpl.getUserById(userId);
    }
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userRepository.save(user);
//            userServiceImpl.createUser(user);
        return new ResponseEntity<>("User added", HttpStatus.OK);
    }


}
