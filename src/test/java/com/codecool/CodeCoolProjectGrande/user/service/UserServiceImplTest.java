package com.codecool.CodeCoolProjectGrande.user.service;

import com.codecool.CodeCoolProjectGrande.user.BanToken;
import com.codecool.CodeCoolProjectGrande.user.SampleUserData;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserType;
import com.codecool.CodeCoolProjectGrande.user.configuration.EmailValidator;
import com.codecool.CodeCoolProjectGrande.user.password_reset.ResetPasswordToken;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
@SpringBootTest
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;


    @Test
    void shouldReturnFalseWhileUserDataIsIncorrect(){
        assertThat(userService.isUserDataValid(SampleUserData.getInvalidUser()), is(false));
    }

    @Test
    void shouldReturnTrueWhileUserDataIsIncorrect(){
        assertThat(userService.isUserDataValid(SampleUserData.getValidUser()), is(true));
    }

    @Test
    void newUserShouldHaveUserType(){
        assertThat(userService.createUser(SampleUserData.getValidUser()).get().getUserType(), equalTo(UserType.USER));
    }


}