package com.apt612.depaybackend.dao.repository;

import com.apt612.depaybackend.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {
    List<Message> getByReceiverId(String userId);
    int countByReceiverIdAndRead(String userId,boolean read);
    List<Message> getByReceiverIdAndSenderId(String userId1, String userId2);
}
