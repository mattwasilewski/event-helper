package com.codecool.CodeCoolProjectGrande;


import com.codecool.CodeCoolProjectGrande.user.BanToken;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserType;
import com.codecool.CodeCoolProjectGrande.user.configuration.EmailValidator;
import com.codecool.CodeCoolProjectGrande.user.passwordreset.PasswordServiceImpl;
import com.codecool.CodeCoolProjectGrande.user.passwordreset.ResetPasswordToken;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;


@SpringBootTest
public class UserTests {



    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordServiceImpl passwordService;



    private final User user = User.builder().userId(UUID.randomUUID())
            .name("Test")
            .age(22)
            .email("email@gmail.com")
            .password("test1")
            .userType(UserType.USER)
            .banToken(initBanToken())
            .location("Warsaw")
            .events(new HashSet<>())
            .build();


    public BanToken initBanToken(){
        BanToken banToken = new BanToken();
        banToken.setBanId(UUID.randomUUID());
        banToken.setBanDays(5);
        return banToken;
    }

    @Test
    public void saveUserTest(){
        userService.createUser(user);
        Assertions.assertTrue(userService.getUserByID(user.getUserId()).isPresent());
        Assertions.assertEquals(1, userService.getUsers().size());
    }





    @Test
    public void setPasswordTokenWhenEmailExist(){
        passwordService.forgotPassword(user.getEmail());
        Assertions.assertEquals((passwordService.forgotPassword(user.getEmail())), new ResponseEntity<>(HttpStatus.OK));

    }

    @Test
    public void passwordTokenWhenEmailNotExistTest(){
        String testMail = "test@test.com";
        passwordService.forgotPassword(user.getEmail());
        Assertions.assertEquals((passwordService.forgotPassword(testMail)), new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @Test
    public void changePasswordWhenTokenExistTest() {
        String newPassword = "testing";
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        user.setResetPasswordToken(resetPasswordToken);
        passwordService.setNewPassword(user.getResetPasswordToken().getTokenId(), newPassword);
        Assertions.assertEquals(passwordService.setNewPassword(user.getResetPasswordToken().getTokenId(), newPassword), new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


    @Test
    public void notChangePasswordWhenTokenNotExistTest() {
        String newPassword = "testing";
        passwordService.setNewPassword(user.getUserId(), newPassword);
        Assertions.assertEquals(passwordService.setNewPassword(user.getUserId(), newPassword), new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Test
    public void isEmailValidatorWorkingWithInvalidMailTest(){
        String testEmail = "sadasdasad";
        Assertions.assertFalse(EmailValidator.patternMatches(testEmail));
    }

    @Test
    public void isEmailValidatorWorkingWithValidMailTest(){
        String testEmail = "test@gmail.com";
        Assertions.assertTrue(EmailValidator.patternMatches(testEmail));
    }

    @Test
    public void isResetPasswordTokenNotExpired(){
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        LocalDate futureDate = LocalDate.now().plusDays(5);
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = futureDate.atStartOfDay(systemTimeZone);
        Date futureUtilDate = Date.from(zonedDateTime.toInstant());
        Assertions.assertTrue(resetPasswordToken.isExpired(futureUtilDate));
    }

    @Test
    public void isResetPasswordTokenExpired(){
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        LocalDate pastDate = LocalDate.now().minusDays(5);
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = pastDate.atStartOfDay(systemTimeZone);
        Date futureUtilDate = Date.from(zonedDateTime.toInstant());
        Assertions.assertFalse(resetPasswordToken.isExpired(futureUtilDate));
    }








}
