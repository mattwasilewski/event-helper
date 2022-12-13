package com.codecool.CodeCoolProjectGrande.user.passwordreset;


import com.codecool.CodeCoolProjectGrande.user.User;
//import com.codecool.CodeCoolProjectGrande.user.UserRepository;
//import com.codecool.CodeCoolProjectGrande.user.UserServiceImpl;
import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import de.codecentric.boot.admin.client.registration.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin
@RestController
public class PasswordController {



    private EmailService emailService;
    private UserServiceImpl userServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);


    @Autowired
    public PasswordController(EmailService emailService, UserServiceImpl userServiceImpl) {
        this.emailService = emailService;
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam("email") String userEmail){
        Optional<User> user = userServiceImpl.getUserByEmail(userEmail);
        System.out.println("1");
        if (user.isPresent()) {
            System.out.println("2");
            String appUrl = "http://localhost:8080";
            ResetPasswordToken token = new ResetPasswordToken();
            user.get().setResetPasswordToken(token);
            userServiceImpl.saveUser(user.get());
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.get().getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset-password/" + token.getTokenId());
            emailService.sendEmail(passwordResetEmail);// TODO only one walid token
            logger.info("Email for password reset send successfully");
            System.out.println("3");
        }
    }

    @PutMapping("/reset-password/{token}")     // TODO change password to request body
    public void setNewPassword(@PathVariable("token") UUID token, @RequestBody String password){
        Optional<User> user = userServiceImpl.getUserByToken(token);
        if (user.isPresent()) {
            User resetUser = user.get();
            if (!resetUser.getResetPasswordToken().isExpired(new Date())) {
                resetUser.setPassword(password);
                resetUser.setResetPasswordToken(null);
                userServiceImpl.saveUser(resetUser);
            } else {
                resetUser.setResetPasswordToken(null);
                System.out.println("Token expired");
            }

        }


    }
}