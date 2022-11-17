package com.codecool.CodeCoolProjectGrande.user.service;

import com.codecool.CodeCoolProjectGrande.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    void saveUser(User user);
    Optional<User> getUserByToken(UUID token);


}