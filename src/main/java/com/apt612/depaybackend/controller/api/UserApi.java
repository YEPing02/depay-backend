package com.apt612.depaybackend.controller.api;

import com.apt612.depaybackend.controller.dto.UserAuthentication;
import com.apt612.depaybackend.controller.dto.UserDto;
import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import com.apt612.depaybackend.controller.security.authentification.TokenUtils;
import com.apt612.depaybackend.exception.InvalidDataException;
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
            String token = TokenUtils.getToken(user);
            String refreshToken = TokenUtils.getRefreshToken(user);
            UserDto userDto = mapper.map(user, UserDto.class);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Token", token);
            headers.add("Refresh", refreshToken);
            headers.add("Access-Control-Expose-Headers", "Token");
            headers.add("Access-Control-Allow-Credentials", "Token");
            headers.add("Access-Control-Expose-Headers", "Refresh");
            headers.add("Access-Control-Allow-Credentials", "Refresh");
            return new ResponseEntity(userDto, headers, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserDto> refresh(@RequestHeader("Token") String token, @RequestHeader("Refresh") String refresh) {
        // find refresh token
        String userId = TokenUtils.getAudience(refresh);
        User user = userService.getUserById(userId);
        if (user != null) {
            if (TokenUtils.verifyAuth(refresh)) {
                // refresh refresh token
                String newRefresh = TokenUtils.getRefreshToken(user);
                user.setRefreshToken(newRefresh);
                userService.update(user);
                // refresh token
                String newToken = TokenUtils.getToken(user);
                UserDto userDto = mapper.map(user, UserDto.class);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Token", newToken);
                headers.add("Refresh", newRefresh);
                headers.add("Access-Control-Expose-Headers", "Token");
                headers.add("Access-Control-Allow-Credentials", "Token");
                headers.add("Access-Control-Expose-Headers", "Refresh");
                headers.add("Access-Control-Allow-Credentials", "Refresh");
                return new ResponseEntity(userDto, headers, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> signOut(@RequestHeader("Token") String token) {
        // disable refresh token
        String userId = TokenUtils.getAudience(token);
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setRefreshToken("");
            userService.update(user);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        try {
            User userCreated = userService.create(user);
            if (userCreated != null) {
                UserDto userDto = mapper.map(user, UserDto.class);
                return new ResponseEntity(userDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } catch (PseudoDupliException | InvalidDataException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/unique")
    public boolean isUniquePseudo(@RequestParam("pseudo") String pseudo) {
        return userService.isUniquePseudo(pseudo);
    }

    @GetMapping("/detail")
    @Authenticated
    public ResponseEntity<UserDto> getUser(@RequestHeader("Token") String token) {
        String userId = TokenUtils.getAudience(token);
        User user = userService.getUserById(userId);
        if (user != null) {
            UserDto userDto = mapper.map(user, UserDto.class);
            return new ResponseEntity(userDto, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
