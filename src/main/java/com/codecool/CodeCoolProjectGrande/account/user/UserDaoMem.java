package com.codecool.CodeCoolProjectGrande.account.user;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoMem implements UserDao {
    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return users.stream().filter(user -> user.getUserId() == id).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void deleteUser(UUID id) {
        if (getUserById(id).isPresent()){
            users.remove(getUserById(id).get());
        } else {
            System.out.println("Place for logger");
        }
    }

    @Override
    public void editUser(UUID id, String name, String password, String email) {
        if (getUserById(id).isPresent()) {
            getUserById(id).get().setName(name);
            getUserById(id).get().setPassword(password);
            getUserById(id).get().setEmail(email);
        } else {
            System.out.println("Place for logger");
        }
    }

}
