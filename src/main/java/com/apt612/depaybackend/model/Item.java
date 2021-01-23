package com.apt612.depaybackend.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class Item {
    String id;
    String name;
    String description;
    float price;
    Date uploadTime;
    String userId;
    @Field
    Boolean isDeleted= false;
}
