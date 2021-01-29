package com.apt612.depaybackend.controller.api;

import com.apt612.depaybackend.controller.dto.MapperUtils;
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
public class ItemApi extends BaseApi {
    ItemService itemService;

    public ItemApi(Mapper mapper, ItemService itemService) {
        super(mapper);
        this.itemService = itemService;
    }

    @GetMapping()
    @Authenticated
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<Item> itemList = itemService.getAllItems();
        List<ItemDto> itemDtoList = mapList(itemList, ItemDto.class);
        return new ResponseEntity(itemDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Authenticated
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") String id) {
        Item item = itemService.getItemById(id);
        if (item != null) {
            ItemDto itemDto = mapper.map(item, ItemDto.class);
            return new ResponseEntity(itemDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Authenticated
    public ResponseEntity<ItemDto> create(@RequestBody Item item) {
        Item itemCreated = itemService.create(item);
        if (itemCreated != null) {
            ItemDto itemDto = map(item, ItemDto.class);
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @Authenticated
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Item item = itemService.delete(id);
        if (item.getIsDeleted()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
