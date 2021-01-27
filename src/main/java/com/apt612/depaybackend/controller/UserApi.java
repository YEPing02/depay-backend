package com.apt612.depaybackend.controller;

import com.apt612.depaybackend.controller.dto.UserAuthentication;
import com.apt612.depaybackend.controller.dto.UserDto;
import com.apt612.depaybackend.controller.security.authentification.TokenUtils;
import com.apt612.depaybackend.exception.PseudoDupliException;
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
    public ResponseEntity<UserDto> signIn(@RequestBody UserAuthentication userAuthentication) {
        User user = userService.login(userAuthentication.getUsername(), userAuthentication.getPassword());
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
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        try {
            User userCreated = userService.create(user);
            if (userCreated != null) {
                UserDto userDto = new UserDto();
                mapper.map(user, userDto);
                return new ResponseEntity(userDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } catch (PseudoDupliException pde) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/unique")
    public boolean isUniquePseudo(@RequestParam("pseudo") String pseudo) {
        return userService.isUniquePseudo(pseudo);
    }
}
