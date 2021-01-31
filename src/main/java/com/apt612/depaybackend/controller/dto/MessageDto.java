package com.apt612.depaybackend.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    String id;
    String receiverId;
    String receiverPseudo;
    String senderId;
    String senderPseudo;
    Date timestamp;
    String itemId;
    String content;
    boolean read;
}
