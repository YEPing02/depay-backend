package com.apt612.depaybackend.dao;

import com.apt612.depaybackend.model.Image;
import com.apt612.depaybackend.model.Item;

import java.util.List;

public interface ImageDao {
    Image add(Image image);
    Image getOneImageByItemId(String itemId);
    List<Image> getImagesByItemId(String itemId);
}
