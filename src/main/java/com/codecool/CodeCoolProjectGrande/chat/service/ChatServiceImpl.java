package com.codecool.CodeCoolProjectGrande.chat.service;

import com.codecool.CodeCoolProjectGrande.chat.Message;
import com.codecool.CodeCoolProjectGrande.chat.Status;
import com.codecool.CodeCoolProjectGrande.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @Override
    public Optional<Message> saveMessage(Message message) {
        if(message.getStatus() != Status.JOIN){
            chatRepository.save(message);
            return Optional.of(message);
        }
        return Optional.empty();
    }

    @Override
    public List<Message> getPublicMessages(String senderName) {
        return chatRepository.findAllPublicMessages(senderName);
    }

    @Override
    public List<Message> getUsersPrivateMessages(String nickname) {
        return chatRepository.findAllMessagesWithUserContribution(nickname);
    }

    @Override
    public List<Message> getMessagesSendToEventChat(String eventId) {
        return chatRepository.findAllMessagesSentToEvent(eventId);
    }

}
