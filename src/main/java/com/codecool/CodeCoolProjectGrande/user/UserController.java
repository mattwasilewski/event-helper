package com.codecool.CodeCoolProjectGrande.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api/users/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    public Optional<User> getUserById(@PathVariable UUID userId) {
            return userService.getUserById(userId);
    }
    @PostMapping("create-user")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
