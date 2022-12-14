package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@CrossOrigin
@RestController
public class PasswordController {

    private PasswordServiceImpl passwordService;

    @Autowired
    public PasswordController(PasswordServiceImpl passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String userEmail){
        return passwordService.forgotPassword(userEmail);

    }

    @PutMapping("/reset-password/{token}")     // TODO change password to request body
    public ResponseEntity<?> setNewPassword(@PathVariable("token") UUID token, @RequestBody String password) {
        return passwordService.setNewPassword(token, password);
    }
}