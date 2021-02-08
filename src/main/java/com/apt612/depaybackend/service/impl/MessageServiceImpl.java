package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.MessageDao;
import com.apt612.depaybackend.model.Message;
import com.apt612.depaybackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {
    MessageDao messageDao;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageDao = messageDao;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public Message getMessageById(String id) {
        return messageDao.getMessageById(id);
    }

    @Override
    public Message createMessage(Message message) {
        Message messageCreated = messageDao.createMessage(message);
        sendWebsocketMessage(messageCreated);
        return messageCreated;
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

    private List<Message> conversationsToLastMessages(Map<String, List<Message>> conversation) {
        List<Message> messageList = new ArrayList<>();
        for (Map.Entry<String, List<Message>> e : conversation.entrySet()) {
            List<Message> oneConversation = e.getValue();
            oneConversation.sort(Comparator.comparing(Message::getTimestamp));
            messageList.add(oneConversation.get(oneConversation.size() - 1));
        }
        return messageList;
    }


    private void sendWebsocketMessage(Message message) {
        if (message != null) {
            String objectUserId = message.getReceiverId();
            this.simpMessagingTemplate.convertAndSend( "/queue/private/"+objectUserId, message);
        }
    }
}
