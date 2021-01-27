package com.apt612.depaybackend.controller;

import com.apt612.depaybackend.controller.dto.UserDto;
import com.apt612.depaybackend.controller.security.authentification.TokenUtils;
import com.apt612.depaybackend.model.User;
import com.apt612.depaybackend.service.UserService;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserApi {
    UserService userService;
    Mapper mapper;

    @Autowired
    public UserApi(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> signIn(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        if (user != null) {
            UserDto userDto = new UserDto();
            String token = TokenUtils.getToken(user);
            mapper.map(user, userDto);
            HttpHeaders headers = new HttpHeaders();
            headers.add("token", token);
            return new ResponseEntity(userDto, headers, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public UserDto createUser(@RequestBody User user) {
        User userCreated = userService.create(user);
        UserDto userDto = new UserDto();
        mapper.map(userCreated, userDto);
        return userDto;
    }
}
