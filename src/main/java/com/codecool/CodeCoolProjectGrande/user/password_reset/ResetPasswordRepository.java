package com.codecool.CodeCoolProjectGrande.user.password_reset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPasswordToken, Long> {

}
