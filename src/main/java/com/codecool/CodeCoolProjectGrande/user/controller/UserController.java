package com.codecool.CodeCoolProjectGrande.user.controller;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.service.UserService;

import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("user/{userEmail}")
    public Optional<User> getUserById(@PathVariable String userEmail) {
        System.out.println(userEmail);
        System.out.println(userService.getUserByEmail(userEmail).get().getEmail());

        return userService.getUserById(UUID.fromString(userEmail));
    }

    @PostMapping("user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
//        userService.createUser(user);
        userService.saveUser(user);

        return new ResponseEntity<>("User added", HttpStatus.OK);
    }



}
