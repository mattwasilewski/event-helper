package com.codecool.CodeCoolProjectGrande.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    void createUser(User user);
    Optional<User> getUserById(String id);
    List<User> getAllUsers();
    void deleteUser(String id);
    void editUser(String id, String name, String password, String email);
    Optional<User> getUserByEmail(String email);
}
