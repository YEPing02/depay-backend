package com.apt612.depaybackend.service;

import com.apt612.depaybackend.model.Item;

import java.util.List;


public interface ItemService {
    Item getItemById(String id);

    Item create(Item item);

    Item delete(String id);

    List<Item> getAllItems();
}
