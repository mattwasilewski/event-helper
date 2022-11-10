package com.codecool.CodeCoolProjectGrande.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userServiceImpl.getUsers();
    }

    @GetMapping("/user/{userId}")
    public Optional<User> getUserByID(@PathVariable UUID userId) {
            return userServiceImpl.getUserById(userId);
    }
    @PostMapping("/user")
    public void createUser(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email) {
        userServiceImpl.createUser(new User(name, password, email));
    }


}
