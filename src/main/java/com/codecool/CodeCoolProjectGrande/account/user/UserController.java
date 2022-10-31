package com.codecool.CodeCoolProjectGrande.account.user;


import lombok.Data;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@Data
public class UserController {
    private UserDaoMem users = new UserDaoMem();

    public UserController() {
        users.addUser(new User(UUID.randomUUID(),
                "Karol", 22, "haslo", "mejl@gmail.com", UserType.USER, "url", "Krakow"));
        users.addUser(new User(UUID.randomUUID(),
                "Mateusz", 26, "haslo2", "mattwasilewski96@gmail.com", UserType.ADMIN, "url", "Warszawa"));
        users.addUser(new User(UUID.randomUUID(),
                "Michal", 26, "haslo3", "michał@gmail.com", UserType.USER, "url", "Golcowa"));
        users.addUser(new User(UUID.randomUUID(),
                "Mateusz", 22, "haslo4", "bartek@wp.pl", UserType.MODERATOR, "url", "Jaworzno"));
    }
}
