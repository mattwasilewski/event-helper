package com.codecool.CodeCoolProjectGrande.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@Data
public class Event {
    private UUID eventID;
    private String name;
    private String description;
    private String logo;
    private String linkToEventPage;
    private int price;
    private String location;
    private boolean verification;
    private LocalDate date;
    private boolean toVerification;
    private EventType eventType;
    private UUID userID;

}
