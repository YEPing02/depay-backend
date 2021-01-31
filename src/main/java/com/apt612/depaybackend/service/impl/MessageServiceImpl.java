package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.MessageDao;
import com.apt612.depaybackend.model.Message;
import com.apt612.depaybackend.service.MessageService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Message> getLastMessages(String userId) {
        List<Message> sendMessageList = messageDao.getBySenderId(userId);
        List<Message> receivedMessageList = messageDao.getByReceiverId(userId);
        Map<String, List<Message>> conversations = new HashMap<>();
        for (Message message : sendMessageList) {
            List<Message> conversation = conversations.getOrDefault(message.getReceiverId(), new ArrayList<>());
            conversation.add(message);
            conversations.put(message.getReceiverId(), conversation);
        }
        for (Message message : receivedMessageList) {
            List<Message> conversation = conversations.getOrDefault(message.getSenderId(), new ArrayList<>());
            conversation.add(message);
            conversations.put(message.getSenderId(), conversation);
        }
        return conversationsToLastMessages(conversations);
    }

    @Override
    public List<Message> getConversation(String userId1, String userId2) {
        return messageDao.getSortedConversation(userId1, userId2);
    }

    private boolean isInSameConversation(Message m1, Message m2) {
        boolean res = false;
        String sm1 = m1.getSenderId();
        String sm2 = m2.getSenderId();
        String rm1 = m1.getReceiverId();
        String rm2 = m1.getReceiverId();
        if ((sm1.equals(sm2) && rm1.equals(rm2))
                || ((sm1.equals(rm2) && rm1.equals(sm2)))) {
            res = true;
        }
        return res;

    }

    private List<Message> conversationsToLastMessages(Map<String, List<Message>> conversation) {
        List<Message> messageList = new ArrayList<>();
        for (Map.Entry<String, List<Message>> e : conversation.entrySet()) {
            List<Message> oneConversation = e.getValue();
            oneConversation.sort(Comparator.comparing(Message::getTimestamp));
            messageList.add(oneConversation.get(oneConversation.size() - 1));
        }
        return messageList;
    }
}
