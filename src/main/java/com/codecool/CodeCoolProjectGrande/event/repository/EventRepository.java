package com.codecool.CodeCoolProjectGrande.event.repository;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByEventId(UUID uuid);
    Optional<Event> findEventByName(String name);

    List<Event> findEventsByEventType(EventType eventType, PageRequest page);

    List<Event> findAllByNameContainingOrDescriptionContaining(String phrase, String phrase2,
                                                               PageRequest pageRequest, Sort sort);

    @Query(value = """
                    SELECT COUNT(*)
                    FROM assigned_users
                    WHERE event_id = CAST(?1 AS UUID)
                    """,
            nativeQuery = true)
    int getNumOfAttendees(String eventId);

    List<Event> findAllByAssignedUsersIn(Set<User> assignedUsers);

    @Transactional
    Optional<Event> removeEventByEventId(UUID eventId);

    Optional<Event> getEventByEventIdAndAssignedUsersIn(UUID eventId, Set<User> user);

    Optional<Event> getEventByEventIdAndUserId(UUID eventId, UUID userId);



}
