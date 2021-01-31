package com.apt612.depaybackend.dao.impl;

import com.apt612.depaybackend.dao.ImageDao;
import com.apt612.depaybackend.dao.repository.ImageRepository;
import com.apt612.depaybackend.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image add(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getOneImageByItemId(String itemId) {
        return imageRepository.getOneImageByItemId(itemId);
    }

    @Override
    public List<Image> getImagesByItemId(String itemId) {
        return imageRepository.getImagesByItemId(itemId);
    }
}
