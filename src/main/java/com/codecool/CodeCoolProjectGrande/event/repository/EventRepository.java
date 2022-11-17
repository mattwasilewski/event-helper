package com.codecool.CodeCoolProjectGrande.event.repository;

import com.codecool.CodeCoolProjectGrande.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventByEventId(UUID uuid);

}
