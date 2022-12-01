package com.codecool.CodeCoolProjectGrande.event_provider;

import com.codecool.CodeCoolProjectGrande.event_provider.model.MainData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataRepository extends JpaRepository<MainData, Long> {
}
