package com.codecool.CodeCoolProjectGrande.user.password_reset;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/api/")
public class PasswordController {

    private PasswordServiceImpl passwordService;

    @Autowired
    public PasswordController(PasswordServiceImpl passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody String email){
        System.out.println("testowy");
        return passwordService.forgotPassword(email);

    }

    @PutMapping("reset-password")
    public ResponseEntity<?> setNewPassword(@RequestParam("token") UUID token, @RequestBody String password) throws JsonProcessingException {
        return passwordService.setNewPassword(token, password);
    }
}