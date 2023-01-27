package com.codecool.CodeCoolProjectGrande.event;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.*;
import java.util.*;


@Builder
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
    @Column(columnDefinition="TEXT")
    private String description;
    private String logo;
    private String linkToEventPage;
    private int price;
    private String location;
    @Enumerated
    private EventStatus eventStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean publicEvent;
    @Enumerated
    private EventType eventType;
    private UUID userId;
    @JsonIgnore
    private Double latitude;
    private Double longitude;
    private String source;
    @JoinColumn(name = "data")
    @OneToOne(cascade=CascadeType.ALL)
    private Image image;
    @ManyToMany
    @JoinTable(
            name = "assigned_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> assignedUsers = new HashSet<>();

    public Event(String name, String description, String url, String location, String logo, EventType eventType,
                 String startDate, String endDate, Double latitude, Double longitude, String source, Set<User> assignedUsers) {
        this.name = name;
        this.description = description;
        this.linkToEventPage = url;
        this.location = location;
        this.logo = logo;
        this.eventType =eventType;
        this.eventStatus = EventStatus.TO_VERIFICATION;
        this.startDate = parseStringToLocalDate(startDate);
        this.endDate = parseStringToLocalDate(endDate);
        this.latitude = latitude;
        this.longitude = longitude;
        this.source = source;
        this.assignedUsers = assignedUsers;

    }

    public void assignUser(User user) {
        assignedUsers.add(user);
    }
    public LocalDateTime parseStringToLocalDate(String date) {
            return LocalDateTime.parse(date);
    }

}
