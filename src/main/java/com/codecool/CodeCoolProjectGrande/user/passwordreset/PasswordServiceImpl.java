package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class PasswordServiceImpl {
    private final ResetPasswordRepository resetPasswordRepository;
    private UserServiceImpl userService;
    private EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);


    @Autowired
    public PasswordServiceImpl(ResetPasswordRepository resetPasswordRepository, UserServiceImpl userService, EmailService emailService) {
        this.resetPasswordRepository = resetPasswordRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public ResponseEntity<?> forgotPassword(String userEmail) {
        Optional<User> user = userService.getUserByEmail(userEmail);
        System.out.println("1");
        if (user.isPresent()) {
            System.out.println("2");
            String appUrl = "http://localhost:8080";
            ResetPasswordToken token = new ResetPasswordToken();
            user.get().setResetPasswordToken(token);
            userService.saveUser(user.get());
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.get().getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset-password/" + token.getTokenId());
            emailService.sendEmail(passwordResetEmail);
            logger.info("Email for password reset send successfully");
            System.out.println("3");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> setNewPassword(UUID token, String password){
        Optional<User> user = userService.getUserByToken(token);
        if (user.isPresent()) {
            User resetUser = user.get();
            if (!resetUser.getResetPasswordToken().isExpired()) {
                resetUser.setPassword(password);
                resetUser.setResetPasswordToken(null);
                userService.saveUser(resetUser);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                resetUser.setResetPasswordToken(null);
                return new ResponseEntity<>(HttpStatus.GONE);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
