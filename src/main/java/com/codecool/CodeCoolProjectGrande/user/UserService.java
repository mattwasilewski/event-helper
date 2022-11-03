package com.codecool.CodeCoolProjectGrande.user;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    public Optional<User> getUserById(UUID id) {
        return UserRepository.USERS_IN_MEMORY.stream().filter(user -> user.getUserId() == id).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return UserRepository.USERS_IN_MEMORY;
    }

    @Override
    public void deleteUser(UUID id) {
        if (getUserById(id).isPresent()){
            UserRepository.USERS_IN_MEMORY.remove(getUserById(id).get());
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
