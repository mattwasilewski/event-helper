package com.codecool.CodeCoolProjectGrande.user.service;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserType;
import com.codecool.CodeCoolProjectGrande.user.auth.LoginRequest;
import com.codecool.CodeCoolProjectGrande.user.configuration.EmailValidator;
import com.codecool.CodeCoolProjectGrande.user.configuration.SecurityConfig;
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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityConfig securityConfig;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.securityConfig = securityConfig;
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
    public Optional<User> saveUser(User user) {
        userRepository.save(user);
        return Optional.of(user);
    }

    public boolean isUserDataValid(User user){
        if (EmailValidator.patternMatches(user.getEmail())
                && user.getPassword().length() >= 8
                && userRepository.findUserByEmail(user.getEmail()).isEmpty()){
            return true;
        }
        return false;
    }

    public Optional<User> createUser(User user){
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setUserType(UserType.USER);
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public ResponseCookie authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return jwtCookie;
    }

    @Override
    public ResponseCookie logoutUser() {
        return jwtUtils.getCleanJwtCookie();
    }

    public void deleteUser(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).get();
        try {
            userRepository.removeReferenceFromAssignedUsers(String.valueOf(user.getUserId()));
        } catch (Exception e) {
            System.out.println("Exception UserServiceImpl -> :101  |  w wolnej chwili ogarnąć    ↓ ↓ ↓ ↓ ↓ ↓");
            System.out.println(e);
        }
        userRepository.deleteUserByEmail(userEmail);
    }

}










