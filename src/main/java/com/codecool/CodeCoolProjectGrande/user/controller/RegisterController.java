package com.codecool.CodeCoolProjectGrande.user.controller;

import com.codecool.CodeCoolProjectGrande.user.UserType;
import com.codecool.CodeCoolProjectGrande.user.configuration.EmailValidator;
import com.codecool.CodeCoolProjectGrande.user.configuration.SecurityConfig;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class RegisterController {
    private final SecurityConfig securityConfig;
    private final UserService userService;


    @Autowired
    public RegisterController(SecurityConfig securityConfig, UserService userService) {
        this.securityConfig = securityConfig;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerAccount(@RequestBody User user){
        if (userService.isUserDataValid(user)){
            userService.createUser(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
}
