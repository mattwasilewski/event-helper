package com.codecool.CodeCoolProjectGrande.user.service;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.auth.LoginRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    void saveUser(User user);
    Optional<User> getUserByToken(UUID token);

    List<User> getUsers();


    ResponseCookie authenticateUser(LoginRequest loginRequest);
    ResponseCookie logoutUser();



}