package com.codecool.CodeCoolProjectGrande.user.controller;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class RegisterController {
    private final UserService userService;


    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/oauthtest")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
        System.out.println("weszlo mi do current user --------------------");
        System.out.println(oAuth2AuthenticationToken.getPrincipal().getAttributes());
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerAccount(@RequestBody User user){
        if (userService.isUserDataValid(user)){
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
