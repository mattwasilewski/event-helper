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
@RequestMapping("/api/user/")
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

    @GetMapping("{userId}")
    public User getUserByID(@PathVariable UUID userId) {
        User user = userService.getUserById(userId).orElse(new User());
//        System.out.println(user.getEvents());
        return user;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
//        userService.createUser(user);
        userService.saveUser(user);

        return new ResponseEntity<>("User added", HttpStatus.OK);
    }



}
