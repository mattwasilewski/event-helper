package com.codecool.CodeCoolProjectGrande.user.passwordreset;

import com.codecool.CodeCoolProjectGrande.user.User;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class ResetPasswordToken {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID tokenId;
    private Date createdDate;

    public ResetPasswordToken(){
        createdDate = new Date();
        tokenId = UUID.randomUUID();
    }

    public boolean isExpired(Date accessDate){
        int expirationDays = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdDate);
        calendar.add(Calendar.DAY_OF_MONTH, expirationDays);
        Date expirationTime = calendar.getTime();
        return accessDate.after(expirationTime);
    }
}
