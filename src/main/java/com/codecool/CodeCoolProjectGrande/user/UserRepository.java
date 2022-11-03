package com.codecool.CodeCoolProjectGrande.user;
import java.util.*;

public interface UserRepository {
    List<User> USERS_IN_MEMORY = new ArrayList<>(Arrays.asList(new User("1",
            "Karol", 22, "haslo", "mejl@gmail.com", UserType.USER, "url", "Krakow", null),
        new User("2",
                "Mateusz", 26, "haslo2", "mattwasilewski96@gmail.com", UserType.ADMIN, "url", "Warszawa", null),
        new User("3",
                "Michal", 26, "haslo3", "michał@gmail.com", UserType.USER, "url", "Golcowa", null),
        new User("4",
                "Bartek", 22, "haslo4", "bartek@wp.pl", UserType.MODERATOR, "url", "Jaworzno", null)));
}
