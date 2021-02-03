package com.apt612.depaybackend.controller.api;

import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import com.apt612.depaybackend.model.Image;
import com.apt612.depaybackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items/{itemId}/images")
@CrossOrigin
public class ImageAPI {
    @Autowired
    ImageService imageService;


    @PostMapping
    @Authenticated
    public ResponseEntity<Image> addImage(@RequestBody Image image){
        return new ResponseEntity<Image>(imageService.add(image),HttpStatus.CREATED);
    }

    @GetMapping
    @Authenticated
    public  ResponseEntity<List<String>> getImagesOfItem(@PathVariable("itemId") String itemId){
        List<Image> images = imageService.getImagesByItemId(itemId);

        if(images.size()==0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
       }

        List<String> rsl = new ArrayList<String>() ;
        for (Image image : images){
            rsl.add(image.getImageBase64());
        }

        return new ResponseEntity<List<String>>(rsl,HttpStatus.OK);
    }

    @GetMapping(value = "/first",produces = "text/plain")
    public  ResponseEntity<String> getFirstImageOfItem(@PathVariable("itemId") String itemId) {
       Image image = imageService.getCoverImageByItemId(itemId);
       if(image != null) return new ResponseEntity<String>(image.getImageBase64(), HttpStatus.OK);

       return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}