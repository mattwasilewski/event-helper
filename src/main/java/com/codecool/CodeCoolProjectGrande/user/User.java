package com.codecool.CodeCoolProjectGrande.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID userId;
    private String name;
    private int age;
    private String password;
    private String email;
    @Enumerated
    private UserType userType;
    private String imgUrl;
    private String location;

}
