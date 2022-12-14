package com.codecool.CodeCoolProjectGrande.event.repository;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByEventId(UUID uuid);
    Optional<Event> findEventByName(String name);

    List<Event> findEventsByEventType(EventType eventType, PageRequest page);

    List<Event> findAllByNameContainingOrDescriptionContaining(String phrase, String phrase2,
                                                               PageRequest pageRequest, Sort sort);

    @Query(nativeQuery = true,
            value = """
            SELECT e.description, e.end_date, e.event_status, e.event_type, e.link_to_event_page,
                e.location, e.logo, e.name, e.price, e.public_event, e.start_date
            FROM events e
            JOIN assigned_users ON assigned_users.event_id = e.event_id
            WHERE assigned_users.user_id = 'bf199137-647e-4808-ae17-9110a1072e90'
            """)
    List<Event> getAssignedEventsToUser();

    List<Event> findAllByAssignedUsersIn(Set<User> assignedUsers);



}
