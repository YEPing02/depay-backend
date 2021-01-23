package com.apt612.depaybackend.controller;

import com.apt612.depaybackend.model.Item;
import com.apt612.depaybackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemApi {
    @Autowired
    ItemService itemService;

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable("id") String id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @DeleteMapping("/{id}")
    public Item delete(@PathVariable("id") String id){return itemService.delete(id);}
}
