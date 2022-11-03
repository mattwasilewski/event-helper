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
    public Optional<User> getUserById(UUID id) {
        return UserRepository.USERS_IN_MEMORY.stream().filter(user -> user.getUserId().equals(id)).findFirst();
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

    @Override
    public Optional<User> getUserByEmail(String email) {
        return UserRepository.USERS_IN_MEMORY.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<User> getUserByResetToken(UUID resetToken) {
        System.out.println(resetToken + " getuserbyresettoken");
        UserRepository.USERS_IN_MEMORY.forEach(user -> System.out.println(user.toString()));
        return UserRepository.USERS_IN_MEMORY.stream()
                .filter(user -> user.getResetToken() == resetToken).findFirst();

    }
    @Override
    public void updateUserToken(String email, UUID token){
        System.out.println(token + " updateUser");
        if (getUserByEmail(email).isPresent()){;
            getUserByEmail(email).get().setResetToken(token);
            System.out.println(getUserByEmail(email).get().getResetToken() + " update2");
        }
    }

}
