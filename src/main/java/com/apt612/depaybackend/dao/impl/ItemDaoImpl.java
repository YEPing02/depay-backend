package com.apt612.depaybackend.dao.impl;

import com.apt612.depaybackend.dao.ItemDao;
import com.apt612.depaybackend.dao.repository.ItemRepository;
import com.apt612.depaybackend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItemById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item deleteItem(String id){
        Item item =  itemRepository.findById(id).orElse(null);
        if(item != null){
            item.setIsDeleted(true);
            itemRepository.save(item);
        }
        return item;
    }
}
