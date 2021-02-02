package com.apt612.depaybackend.dao.repository;

import com.apt612.depaybackend.model.Image;
import com.apt612.depaybackend.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ImageRepository extends MongoRepository<Image, String> {
    //@Query(value="{'itemID':?0}")
    //List<Image> getImagesByItemId(String itemId);

    List<Image> getByItemId(String itemId);

}
