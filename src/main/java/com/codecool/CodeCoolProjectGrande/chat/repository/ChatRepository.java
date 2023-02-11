package com.codecool.CodeCoolProjectGrande.chat.repository;

import com.codecool.CodeCoolProjectGrande.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Message, UUID> {
    @Query( value= """
            SELECT m.message_id, m.sender_name, m.receiver_name, m.message, m.status,m.date
            FROM messages m 
            WHERE m.receiver_name = 'public' AND m.status = 1
            """, nativeQuery = true)
    List<Message> findAllPublicMessages(String senderName);

    @Query( value= """
            SELECT m.message_id, m.sender_name, m.receiver_name, m.message, m.status, m.date
            FROM messages m
            WHERE m.receiver_name = :nickname OR (m.sender_name = :nickname AND (m.receiver_name != 'public'
            AND m.receiver_name NOT IN (SELECT CAST(event_id AS varchar) from events WHERE CAST(event_id AS varchar) = m.receiver_name)));
                                                                                                            
            """, nativeQuery = true)
    List<Message> findAllMessagesWithUserContribution(String nickname);

    @Query( value= """
            SELECT m.message_id, m.sender_name, m.receiver_name, m.message, m.status,m.date 
            FROM messages m 
            WHERE m.receiver_name = :eventId OR (m.sender_name = :eventId AND m.receiver_name != 'public')
            """, nativeQuery = true)
    List<Message> findAllMessagesSentToEvent(String eventId);
}
