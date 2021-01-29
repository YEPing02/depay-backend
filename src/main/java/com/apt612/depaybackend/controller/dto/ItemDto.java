package com.apt612.depaybackend.controller.dto;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import java.util.Date;
@Data
public class ItemDto extends RepresentationModel<ItemDto> {
    String id;
    String name;
    String description;
    float price;
    Date uploadTime;
    String userId;
    String pseudo;
}
