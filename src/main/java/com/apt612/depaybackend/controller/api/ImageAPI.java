package com.apt612.depaybackend.controller.api;

import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import com.apt612.depaybackend.model.Image;
import com.apt612.depaybackend.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/{itemId}/image")
@CrossOrigin
public class ImageAPI {
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

    @GetMapping("first")
    public  ResponseEntity<Image> getFirstImageOfItem(@PathVariable("itemId") String itemId) {
        return new ResponseEntity<Image>(imageService.getOneImageByItemId(itemId),HttpStatus.FOUND);
    }
}