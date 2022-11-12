package com.codecool.CodeCoolProjectGrande.user;

import com.codecool.CodeCoolProjectGrande.user.passwordreset.ResetPasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findUserByUserId(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        if (getUserById(id).isPresent()){
            userRepository.delete(getUserById(id).get());
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
        return getUsers().stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<User> getUserByToken(UUID token) {
        return userRepository.findUserByResetPasswordTokenTokenId(token);
    }

    @Override
    @Modifying
    public void saveUser(User user) {
        userRepository.save(user);
    }


}

