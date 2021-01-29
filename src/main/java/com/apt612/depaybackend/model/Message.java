package com.apt612.depaybackend.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    String id;
    String receiverId;
    String senderId;
    Date timestamp;
    String itemId;
    String context;
    boolean read;
}
