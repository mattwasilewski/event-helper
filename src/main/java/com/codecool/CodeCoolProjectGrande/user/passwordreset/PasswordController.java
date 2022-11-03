package com.codecool.CodeCoolProjectGrande.user.passwordreset;


import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@RestController
public class PasswordController {



    private EmailService emailService;
    private UserService userService;

    @Autowired
    public PasswordController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam("email") String userEmail, HttpServletRequest request){
        Optional<User> user = userService.getUserByEmail(userEmail);
        if (userService.getUserByEmail(userEmail).isPresent()) {
            String resetToken = UUID.randomUUID().toString();
            System.out.println(resetToken + "forgotpass");
            userService.updateUserToken(userEmail, resetToken);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.get().getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + user.get().getResetToken());

            emailService.sendEmail(passwordResetEmail);
        }

    }

    @GetMapping("/reset-password")
    public void resetPasswordPage(@RequestParam("token") String token) {
        System.out.println(token + "resetpasspge");
        Optional<User> user = userService.getUserByResetToken(token);
        if (user.isPresent()) { // Token found in DB
            System.out.println("Add parameter token: " + token);
        } else { // Token not found in DB
            System.out.println("error message");
        }
    }

    @PostMapping("/reset-password")
    public void setNewPassword(@RequestParam("token") String token, @RequestParam("password") String password){
        Optional<User> user = userService.getUserByResetToken(token);
        if (user.isPresent()){
            User resetUser = user.get();
            resetUser.setPassword(password);
            resetUser.setResetToken(null);
        }

        }
    }
