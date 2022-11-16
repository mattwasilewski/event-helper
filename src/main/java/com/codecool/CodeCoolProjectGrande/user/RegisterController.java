package com.codecool.CodeCoolProjectGrande.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class RegisterController {
    private UserRepository userRepository;
    private SecurityConfig securityConfig;

    @Autowired
    public RegisterController(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @PostMapping("/registration")
    public ResponseEntity registerAccount(@RequestBody User user){
        System.out.println(user);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
