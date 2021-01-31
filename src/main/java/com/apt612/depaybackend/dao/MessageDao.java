package com.apt612.depaybackend.dao;

import com.apt612.depaybackend.model.Message;

import java.util.List;

public interface MessageDao {
    Message getMessageById(String id);

    List<Message> getMessagesByReceiver(String userId);

    int getUnreadNumberByUserId(String userId);

    List<Message> getSortedConversation(String userId1, String userId2);
    List<Message> getByReceiverId(String userId);
    List<Message> getBySenderId(String userId);

    Message createMessage(Message message);
}
