package com.apt612.depaybackend.service;

import com.apt612.depaybackend.model.Image;

import java.util.List;


public interface ImageService {
    Image add(Image image);
    List<Image> getImagesByItemId(String itemId);
    Image getOneImageByItemId(String itemId);
}
