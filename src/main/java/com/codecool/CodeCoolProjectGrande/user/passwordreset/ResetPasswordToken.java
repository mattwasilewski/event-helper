package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ResetPasswordToken {

    private final String tokenID = UUID.randomUUID().toString();
    private User user;
    private final Date createdDate;


}
