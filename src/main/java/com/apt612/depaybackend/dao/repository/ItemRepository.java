package com.apt612.depaybackend.dao.repository;

import com.apt612.depaybackend.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> getByIsDeleted(boolean deleted);
}
