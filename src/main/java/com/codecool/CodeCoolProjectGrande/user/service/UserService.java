package com.codecool.CodeCoolProjectGrande.user.service;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> getUserByEmail(String email);
    void saveUser(User user);
    Optional<User> getUserByToken(UUID token);

    List<User> getUsers();

    void createUser(User user);

    Optional<User> getUserByID(UUID userId);


}