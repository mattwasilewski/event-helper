package com.codecool.CodeCoolProjectGrande.user.service;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.auth.LoginRequest;
import com.codecool.CodeCoolProjectGrande.user.jwt.JwtUtils;
import com.codecool.CodeCoolProjectGrande.user.repository.UserDetailsImpl;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findUserByUserId(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> getUserByToken(UUID token) {
        return userRepository.findUserByResetPasswordTokenTokenId(token);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Modifying
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public ResponseCookie authenticateUser(LoginRequest loginRequest) {
        System.out.println("WESZLO DO AUTHENTICATE USER IN SERVICE IMPL AAAAAAAAA");
        System.out.println(loginRequest.getEmail());
        System.out.println(loginRequest.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        System.out.println("authentication. get principal: ? " + authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("PRZESZLO AUTHENTICATE CZY JEDNAK NIE PRZESZLO?");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return jwtCookie;
    }

    @Override
    public ResponseCookie logoutUser() {
        return jwtUtils.getCleanJwtCookie();
    }


}