package com.apt612.depaybackend.controller.api;

import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import com.apt612.depaybackend.model.Image;
import com.apt612.depaybackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public  ResponseEntity<List<Image>> getImagesOfItem(@PathVariable("itemId") String itemId){
        return new ResponseEntity<List<Image>>(imageService.getImagesByItemId(itemId),HttpStatus.FOUND);
    }

    @GetMapping(value = "/first")
    public  ResponseEntity<String> getFirstImageOfItem(@PathVariable("itemId") String itemId) {
       Image image = imageService.getOneImageByItemId(itemId);
       if(image != null) return new ResponseEntity<String>(image.getImageBase64(), HttpStatus.FOUND);

       return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}