package com.codecool.CodeCoolProjectGrande.user.passwordreset;


import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PasswordController {



    private EmailService emailService;
    private UserServiceImpl userServiceImpl;


    @Autowired
    public PasswordController(EmailService emailService, UserServiceImpl userServiceImpl) {
        this.emailService = emailService;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam("email") String userEmail, HttpServletRequest request){
        Optional<User> user = userServiceImpl.getUserByEmail(userEmail);
        if (user.isPresent()) {
            ResetPasswordToken token = new ResetPasswordToken();
            user.get().setResetPasswordToken(token);
            userServiceImpl.saveUser(user.get());
            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.get().getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset-password/?token=" + token.getTokenId());
            emailService.sendEmail(passwordResetEmail);// TODO only one walid token

        }

    }

    @GetMapping("/reset-password")
    public void resetPasswordPage(@RequestParam("token") UUID token) {
        Optional<User> user = userServiceImpl.getUsers().stream()
                .filter(user1 -> user1.getResetPasswordToken().getTokenId().equals(token)).findFirst();
        if (user.isPresent()) { // Token found in DB
            System.out.println("Add parameter token: " + token);
        } else { // Token not found in DB
            System.out.println("error message");
        }
    }

    @PutMapping("/reset-password/{token}")     // TODO change password to request body
    public void setNewPassword(@PathVariable("token") UUID token, @RequestParam("password") String password){
        Optional<User> user = userServiceImpl.getUserByToken(token);
        if (user.isPresent()) {
            user.get().setPassword(password);
            user.get().setResetPasswordToken(null);
            userServiceImpl.saveUser(user.get());
        }


        }
    }
