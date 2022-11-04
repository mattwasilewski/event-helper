package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.User;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ResetPasswordToken {

    private String tokenID;
    private User user;
    private Date createdDate;

    public ResetPasswordToken(User user){
        this.user = user;
        createdDate = new Date();
        tokenID = UUID.randomUUID().toString();

    }

}
