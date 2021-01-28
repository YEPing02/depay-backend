package com.apt612.depaybackend.model;

import lombok.Data;

@Data
public class User {
    String id;
    String pseudo;
    String wechatId;
    String email;
    String phone;
    String zone;
    String password;
    String refreshToken;
}
