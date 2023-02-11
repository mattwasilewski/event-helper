package com.codecool.CodeCoolProjectGrande.event;
import com.codecool.CodeCoolProjectGrande.image.Image;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @OneToMany
    @JoinTable(
            name = "gallery",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> gallery = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "assigned_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> assignedUsers = new HashSet<>();

    public Event(String name, String description, String url, String location, Image image, EventType eventType,
                 String startDate, String endDate, Double latitude, Double longitude, String source,
                 Set<User> assignedUsers, Set<Image> gallery) {
        this.name = name;
        this.description = description;
        this.linkToEventPage = url;
        this.location = location;
        this.image = image;
        this.eventType =eventType;
        this.eventStatus = EventStatus.TO_VERIFICATION;
        this.startDate = parseStringToLocalDate(startDate);
        this.endDate = parseStringToLocalDate(endDate);
        this.latitude = latitude;
        this.longitude = longitude;
        this.source = source;
        this.assignedUsers = assignedUsers;
        this.gallery = gallery;
    }

    public void assignUser(User user) {
        assignedUsers.add(user);
    }

    public void removeUSer(User user) {
        assignedUsers.remove(user);
    }

    public LocalDateTime parseStringToLocalDate(String date) {
            return LocalDateTime.parse(date);
    }

}
