package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.MessageDao;
import com.apt612.depaybackend.model.Message;
import com.apt612.depaybackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Message getMessageById(String id) {
        return messageDao.getMessageById(id);
    }

    @Override
    public Message createMessage(Message message) {
        return messageDao.createMessage(message);
    }

    @Override
    public List<Message> getMessagesByReceiver(String userId) {
        return messageDao.getMessagesByReceiver(userId);
    }

    @Override
    public int getUnreadNumberByUserId(String userId) {
        return messageDao.getUnreadNumberByUserId(userId);
    }

    @Override
    public List<Message> getSortedConversation(String userId1, String userId2) {
        return messageDao.getSortedConversation(userId1,userId2);
    }
}
