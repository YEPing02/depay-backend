package com.apt612.depaybackend.controller;

import com.apt612.depaybackend.model.User;
import com.apt612.depaybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserApi {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {return userService.getUserById("id");}

    @PostMapping
    public User createUser(@RequestBody User user ){return userService.create(user);}
}
