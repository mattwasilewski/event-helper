package com.codecool.CodeCoolProjectGrande.user.controller;

import com.codecool.CodeCoolProjectGrande.user.User;

import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getUserByEmail(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @PostMapping("user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("User added", HttpStatus.OK);
    }

    @DeleteMapping("delete-account/{userEmail}")
    public void deleteUser(@PathVariable String userEmail) {
        userService.deleteUser(userEmail);
    }


}
