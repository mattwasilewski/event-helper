package com.codecool.CodeCoolProjectGrande.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class BanToken {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID banId;
    private int banDays;
    private Date expirationDate;
    private String description;

    public BanToken(int banDays, String description) {
        this.banId = UUID.randomUUID();
        this.banDays = banDays;
        this.expirationDate = getExpirationDate(banDays);
        this.description = description;
    }


    public Date getExpirationDate(int expirationDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, expirationDays);
        return calendar.getTime();
    }

    public boolean isExpired() {
        return new Date().after(expirationDate);
    }
}
