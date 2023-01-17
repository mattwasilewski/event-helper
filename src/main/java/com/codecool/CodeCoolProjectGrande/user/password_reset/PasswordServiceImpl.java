package com.codecool.CodeCoolProjectGrande.user.password_reset;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.configuration.SecurityConfig;
import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.UUID;


@Service
public class PasswordServiceImpl {
    private final ResetPasswordRepository resetPasswordRepository;
    private UserServiceImpl userService;
    private EmailService emailService;
    private final SecurityConfig securityConfig;
    private static final Logger logger = LoggerFactory.getLogger(PasswordController.class);


    @Autowired
    public PasswordServiceImpl(ResetPasswordRepository resetPasswordRepository, UserServiceImpl userService, EmailService emailService, SecurityConfig securityConfig) {
        this.resetPasswordRepository = resetPasswordRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.securityConfig = securityConfig;
    }

    public ResponseEntity<?> forgotPassword(String userEmail) {
        Optional<User> user = userService.getUserByEmail(userEmail.replaceAll("\"", ""));
        if (user.isPresent()) {
            ResetPasswordToken token = setResetPasswordToken(user.get());
            sendResetPasswordEmail(user.get(), token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void sendResetPasswordEmail(User user, ResetPasswordToken token) {
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        String appUrl = "http://localhost:3000";
        passwordResetEmail.setFrom("support@demo.com");
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                + "/reset-password/" + token.getTokenId());
        emailService.sendEmail(passwordResetEmail);
    }

    private ResetPasswordToken setResetPasswordToken(User user) {
        ResetPasswordToken token = new ResetPasswordToken();
        user.setResetPasswordToken(token);
        userService.saveUser(user);
        return token;
    }


    public ResponseEntity<?> setNewPassword(UUID token, String password) throws JsonProcessingException{
        Optional<User> user = userService.getUserByToken(token);
        if (user.isPresent()) {
            User resetUser = user.get();
            if (!resetUser.getResetPasswordToken().isExpired()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(password);
                String password1 = jsonNode.get("password").asText();
                resetUser.setPassword(securityConfig.passwordEncoder().encode(password1));
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
