package com.codecool.CodeCoolProjectGrande.account.user;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    void addUser(User user);
    User getUser(UUID id);
    List<User> getAllUsers();
    void deleteUser(UUID id);
    void editUser(UUID id);
}
