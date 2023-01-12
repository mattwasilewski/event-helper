package com.codecool.CodeCoolProjectGrande.user.password_reset;

import com.codecool.CodeCoolProjectGrande.user.configuration.EmailValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResetPasswordTokenTest {

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
        resetPasswordToken.setCreatedDate(futureUtilDate);
        Assertions.assertFalse(resetPasswordToken.isExpired());
    }

    @Test
    public void isResetPasswordTokenExpired(){
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        LocalDate pastDate = LocalDate.now().minusDays(5);
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = pastDate.atStartOfDay(systemTimeZone);
        Date pastUtilDate = Date.from(zonedDateTime.toInstant());
        resetPasswordToken.setCreatedDate(pastUtilDate);
        Assertions.assertTrue(resetPasswordToken.isExpired());
    }

}