package com.codecool.CodeCoolProjectGrande.user.repository;
import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface UserRepository extends JpaRepository<User, UUID>, UserDetailsService {
    Optional<User> findUserByUserId(UUID id);
    Optional<User> findUserByEmail(String email);
    List<User> findByEmail(String email);
    Optional<User> findUserByResetPasswordTokenTokenId(UUID token);

    UserDetails loadUserByUsername(String username);
}