package com.apt612.depaybackend.service;

import com.apt612.depaybackend.model.Item;


public interface ItemService {
    Item getItemById(String id);

    Item create(Item item);

    Item delete(String id);
}
