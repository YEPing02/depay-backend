package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.model.Item;
import com.apt612.depaybackend.service.ItemService;
import com.apt612.depaybackend.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;

    @Override
    public Item getItemById(String id) {
        return itemDao.getItemById(id);
    }

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public  Item delete(String id){return itemDao.deleteItem(id);}

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }
}
