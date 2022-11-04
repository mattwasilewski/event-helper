package com.codecool.CodeCoolProjectGrande.user.passwordreset;


import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("emailService")
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    // @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
