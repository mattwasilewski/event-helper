package com.codecool.CodeCoolProjectGrande.account.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    void addUser(User user);
    Optional<User> getUserById(UUID id);
    List<User> getAllUsers();
    void deleteUser(UUID id);
    void editUser(UUID id, String name, String password, String email);
}
