package com.apt612.depaybackend.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    String id;
    String receiverId;
    String receiverPseudo;
    String senderId;
    String senderPseudo;
    Date timestamp;
    String itemId;
    String content;
    boolean read=false;
}
