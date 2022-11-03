package com.codecool.CodeCoolProjectGrande.user;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDao {
    @Override
    public void createUser(User user) {
        UserRepository.USERS_IN_MEMORY.add(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return UserRepository.USERS_IN_MEMORY.stream().filter(user -> user.getUserId() == id).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return UserRepository.USERS_IN_MEMORY;
    }

    @Override
    public void deleteUser(String id) {
        if (getUserById(id).isPresent()){
            UserRepository.USERS_IN_MEMORY.remove(getUserById(id).get());
        } else {
            System.out.println("Place for logger");
        }
    }

    @Override
    public void editUser(String id, String name, String password, String email) {
        if (getUserById(id).isPresent()) {
            getUserById(id).get().setName(name);
            getUserById(id).get().setPassword(password);
            getUserById(id).get().setEmail(email);
        } else {
            System.out.println("Place for logger");
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return UserRepository.USERS_IN_MEMORY.stream().filter(user -> user.getEmail() == email).findFirst();
    }

    public void updateUserToken(String email, UUID token){
        if (getUserByEmail(email).isPresent()){
            getUserByEmail(email).get().setResetToken(token.toString());
        }
    }

}
