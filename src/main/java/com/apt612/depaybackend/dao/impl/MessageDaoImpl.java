package com.apt612.depaybackend.dao.impl;

import com.apt612.depaybackend.dao.MessageDao;
import com.apt612.depaybackend.dao.repository.MessageRepository;
import com.apt612.depaybackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
    MessageRepository messageRepository;

    @Autowired
    public MessageDaoImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message getMessageById(String id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> getMessagesByReceiver(String userId) {
        return messageRepository.getByReceiverId(userId);
    }

    @Override
    public int getUnreadNumberByUserId(String userId) {
        return messageRepository.countByReceiverIdAndRead(userId, false);
    }

    @Override
    public List<Message> getSortedConversation(String userId1, String userId2) {
        List<Message> conversation = new ArrayList<>();
        List<Message> messages1 = messageRepository.getByReceiverIdAndSenderId(userId1, userId2);
        List<Message> messages2 = messageRepository.getByReceiverIdAndSenderId(userId2, userId2);
        conversation.addAll(messages1);
        conversation.addAll(messages2);
        conversation.sort(Comparator.comparing(Message::getTimestamp));
        return conversation;
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
}
