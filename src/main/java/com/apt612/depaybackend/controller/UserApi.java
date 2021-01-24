package com.apt612.depaybackend.controller;

import com.apt612.depaybackend.exception.PseudoDupliException;
import com.apt612.depaybackend.model.User;
import com.apt612.depaybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserApi {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {return userService.getUserById("id");}

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user ){
        try {
            return new ResponseEntity<User>(userService.create(user), HttpStatus.CREATED);
        }
        catch (PseudoDupliException pde){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/unique")
    public  boolean isUniquePseudo(@RequestParam("pseudo") String pseudo) {return userService.isUniquePseudo(pseudo);}
}
