package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.ImageDao;
import com.apt612.depaybackend.model.Image;
import com.apt612.depaybackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageDao imageDao;

    @Override
    public Image add(Image image) {
        return imageDao.add(image);
    }

    @Override
    public Image getCoverImageByItemId(String id) {
        return imageDao.getFirstImageByItemId(id);
    }

    @Override
    public List<Image> getImagesByItemId(String itemId) {
        return imageDao.getImagesByItemId(itemId);
    }
}
