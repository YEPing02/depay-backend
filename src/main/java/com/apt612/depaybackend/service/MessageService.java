package com.apt612.depaybackend.service;

import com.apt612.depaybackend.model.Message;

import java.util.List;

public interface MessageService {
    Message getMessageById(String id);
    Message createMessage(Message message);
    List<Message> getMessagesByReceiver(String userId);
    int getUnreadNumberByUserId(String userId);
    List<Message> getLastMessages(String userId);
    List<Message> getConversation(String userId1,String userId2);
}
