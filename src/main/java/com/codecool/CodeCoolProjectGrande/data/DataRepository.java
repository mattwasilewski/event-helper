package com.codecool.CodeCoolProjectGrande.data;

import com.codecool.CodeCoolProjectGrande.data.model.MainData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DataRepository extends JpaRepository<MainData, Long> {
}
