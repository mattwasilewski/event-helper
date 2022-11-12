package com.codecool.CodeCoolProjectGrande.user;
import com.codecool.CodeCoolProjectGrande.user.passwordreset.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(UUID id);
    Optional<User> findUserByResetPasswordToken(ResetPasswordToken token);
    Optional<User> findUserByResetPasswordTokenTokenId(UUID token);
//    List<User> USERS_IN_MEMORY = new ArrayList<>(Arrays.asList(new User(UUID.randomUUID(),
//            "Karol", 22, "haslo", "mejl@gmail.com", UserType.USER, "url", "Krakow"),
//        new User(UUID.randomUUID(),
//                "Mateusz", 26, "haslo2", "mattwasilewski96@gmail.com", UserType.ADMIN, "url", "Warszawa"),
//        new User(UUID.randomUUID(),
//                "Michal", 26, "haslo3", "michał@gmail.com", UserType.USER, "url", "Golcowa"),
//        new User(UUID.randomUUID(),
//                "Bartek", 22, "haslo4", "bartek@wp.pl", UserType.MODERATOR, "url", "Jaworzno")));
}
