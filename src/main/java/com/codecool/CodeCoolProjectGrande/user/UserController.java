package com.codecool.CodeCoolProjectGrande.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserById(@PathVariable String userId) {
            System.out.println(userService.getUserById("1"));
            return userService.getUserById(userId);
    }
    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }


}
