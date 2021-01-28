package com.apt612.depaybackend.controller;

import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import com.apt612.depaybackend.controller.dto.ItemDto;
import com.apt612.depaybackend.model.Item;
import com.apt612.depaybackend.service.ItemService;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemApi {
    ItemService itemService;
    Mapper mapper;

    @Autowired
    public ItemApi(ItemService itemService, Mapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @GetMapping()
    @Authenticated
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<Item> itemList = itemService.getAllItems();
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Item item : itemList
        ) {
            ItemDto itemDto = new ItemDto();
            mapper.map(item, itemDto);
            itemDto.add(linkTo(methodOn(ItemApi.class).getItemById(itemDto.getId())).withSelfRel());
            itemDtoList.add(itemDto);
        }
        return new ResponseEntity(itemDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Authenticated
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") String id) {
        Item item = itemService.getItemById(id);
        if (item != null) {
            ItemDto itemDto = new ItemDto();
            mapper.map(item, itemDto);
            return new ResponseEntity(itemDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Authenticated
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @DeleteMapping("/{id}")
    @Authenticated
    public Item delete(@PathVariable("id") String id) {
        return itemService.delete(id);
    }
}
