package com.codecool.CodeCoolProjectGrande.event.repository;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByEventId(UUID uuid);

    List<Event> findEventsByEventType(String eventType);

    List<Event> findAllByNameContainingOrDescriptionContaining(String phrase, String phrase2, Sort sort);

}
