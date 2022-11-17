package com.codecool.CodeCoolProjectGrande.event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID eventId = UUID.randomUUID();
    private String name;
    private String description;
    private String logo;
    private String linkToEventPage;
    private int price;
    private String location;
    @Enumerated
    private EventStatus eventStatus;
    private LocalDate date;
    private boolean publicEvent;
    @Enumerated
    private EventType eventType;
    private UUID userId;

    public Event(String name, String description, String logo, EventType eventType) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.eventType =eventType;
        this.eventStatus = EventStatus.TO_VERIFICATION;
    }
}
