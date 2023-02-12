package com.codecool.CodeCoolProjectGrande.chat.service;

import com.codecool.CodeCoolProjectGrande.chat.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ChatService {

    Optional<Message> saveMessage(Message message);
    List<Message> getPublicMessages(String senderName);

    List<Message> getUsersPrivateMessages(String nickname);

    List<Message> getMessagesSendToEventChat(String eventId);
}
