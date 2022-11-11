package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class ResetPasswordToken {
    @Id
    private String tokenId;
    private Date createdDate;
    @JoinColumn(unique = true)
    @OneToOne
    private User user;

    public ResetPasswordToken(){
        createdDate = new Date();
        tokenId = UUID.randomUUID().toString();
            // TODO token status validation
    }

}
