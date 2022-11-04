package com.codecool.CodeCoolProjectGrande.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
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
