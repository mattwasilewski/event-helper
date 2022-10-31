package com.codecool.CodeCoolProjectGrande.account.user;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDaoMem implements UserDao {
    private List<User> users;

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUser(UUID id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public void editUser(UUID id) {

    }
}
