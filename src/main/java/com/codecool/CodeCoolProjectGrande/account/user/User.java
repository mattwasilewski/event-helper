package com.codecool.CodeCoolProjectGrande.account.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class User {
    private UUID userId;
    private String name;
    private int age;
    private String password;
    private String email;
    private UserType userType;
    private String imgUrl;
    private String location;

}
