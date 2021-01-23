package com.apt612.depaybackend.dao.repository;

import com.apt612.depaybackend.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item,String> {
}
