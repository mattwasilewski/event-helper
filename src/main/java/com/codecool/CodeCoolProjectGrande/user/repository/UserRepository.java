package com.codecool.CodeCoolProjectGrande.user.repository;
import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByUserId(UUID id);
    Optional<User> findUserByEmail(String email);
    List<User> findByEmail(String email);
    Optional<User> findUserByResetPasswordTokenTokenId(UUID token);
    @Transactional
    void deleteUserByEmail(String eventId);

    @Query(value = """
                   DELETE FROM assigned_users
                   WHERE user_id = CAST(?1 AS UUID)
                   """,
           nativeQuery = true)
    void removeReferenceFromAssignedUsers(String uuid);

}