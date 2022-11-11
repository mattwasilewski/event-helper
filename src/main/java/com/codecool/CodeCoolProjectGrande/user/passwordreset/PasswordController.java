package com.codecool.CodeCoolProjectGrande.user.passwordreset;


import com.codecool.CodeCoolProjectGrande.user.User;
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
    private PasswordServiceImpl passwordServiceImpl;

    @Autowired
    public PasswordController(EmailService emailService, UserServiceImpl userServiceImpl, PasswordServiceImpl passwordServiceImpl) {
        this.emailService = emailService;
        this.userServiceImpl = userServiceImpl;
        this.passwordServiceImpl = passwordServiceImpl;
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam("email") String userEmail, HttpServletRequest request){
        Optional<User> user = userServiceImpl.getUserByEmail(userEmail);
        if (user.isPresent()) {
            System.out.println(user.get());
            ResetPasswordToken token = new ResetPasswordToken();
            token.setUser(user.get());
            passwordServiceImpl.addToken(token);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.get().getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset-password/?token=" + token.getTokenId());
            emailService.sendEmail(passwordResetEmail);// TODO only one walid token
            token.setUser(user.get());
            System.out.println(token.getTokenId());

        }

    }

    @GetMapping("/reset-password")
    public void resetPasswordPage(@RequestParam("token") String token) {
        Optional<ResetPasswordToken> resetToken = passwordServiceImpl.getTokenByTokenId(token);
        if (resetToken.isPresent()) { // Token found in DB
            System.out.println("Add parameter token: " + token);
        } else { // Token not found in DB
            System.out.println("error message");
        }
    }

    @PutMapping("/reset-password/{token}")     // TODO change password to request body
    public void setNewPassword(@PathVariable("token") String token, @RequestParam("password") String password){
        Optional<ResetPasswordToken> resetToken = passwordServiceImpl.getTokenByTokenId(token);
        if (resetToken.isPresent()) {
            resetToken.get().getUser().setPassword(password);
            userServiceImpl.updatePassword(resetToken.get().getUser());
        }


        }
    }
