package com.apt612.depaybackend.dao;

import com.apt612.depaybackend.model.Item;

import java.util.ArrayList;
import java.util.List;

public interface ItemDao {
    Item getItemById(String id);
    Item create(Item item);
    Item deleteItem(String id);

    List<Item> getAllItems();
}
