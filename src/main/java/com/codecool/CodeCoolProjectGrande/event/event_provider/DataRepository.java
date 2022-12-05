package com.codecool.CodeCoolProjectGrande.event.event_provider;

import com.codecool.CodeCoolProjectGrande.event.event_provider.model.ExtendedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataRepository extends JpaRepository<ExtendedEvent, Long> {
}
