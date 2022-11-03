package com.codecool.CodeCoolProjectGrande.user.passwordreset;


import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

public class PasswordController {



    private EmailService emailService;
    private UserService userService;

    @Autowired
    public PasswordController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping("/forgot-password/{email}")
    public void forgotPassword(@PathVariable("email") String email, @RequestParam("token") UUID token){
        System.out.println(userService.getUserById("1"));
        if (userService.getUserByEmail(email).isPresent()) {
            userService.updateUserToken(email, token);
            System.out.println("dupa");
        }
//        if (!user.isPresent()){
//            System.out.println("logger and error");
//        } else {
//            user.get().setResetToken(UUID.randomUUID().toString());
//            String appUrl = request.getScheme() + "://" + request.getServerName();
//            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//            passwordResetEmail.setFrom("mattwasilewski96@gmail.com");
//            passwordResetEmail.setTo(user.get().getEmail());
//            passwordResetEmail.setSubject("Password Reset Request");
//            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
//                    + "/reset?token=" + user.get().getResetToken());
//            emailService.sendEmail(passwordResetEmail);
//        }

    }
}
