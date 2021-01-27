package com.apt612.depaybackend.controller.dto;

import lombok.Data;

@Data
public class UserDto {
    String id;
    String pseudo;
    String wechatId;
    String email;
    String phone;
    String zone;
}
