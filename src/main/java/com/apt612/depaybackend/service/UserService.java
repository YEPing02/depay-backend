package com.apt612.depaybackend.service;

import com.apt612.depaybackend.model.User;


public interface UserService {
    User create(User user);
    User getUserById(String id);
    Boolean isUniquePseudo (String pseudo);

}
