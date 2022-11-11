package com.codecool.CodeCoolProjectGrande.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void createUser(User user);
    Optional<User> getUserById(UUID id);
    List<User> getUsers();
    void deleteUser(UUID id);
    void editUser(UUID id, String name, String password, String email);
    Optional<User> getUserByEmail(String email);
    void updatePassword(User user);

}
