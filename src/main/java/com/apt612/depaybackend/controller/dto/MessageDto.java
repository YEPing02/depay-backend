package com.apt612.depaybackend.controller.dto;

import java.util.Date;

public class MessageDto {
    String id;
    String receiverId;
    String senderId;
    Date timestamp;
    String context;
    boolean read;
}
