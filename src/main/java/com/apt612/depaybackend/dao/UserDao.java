package com.apt612.depaybackend.dao;

import com.apt612.depaybackend.model.User;

public interface UserDao {
    User create(User user);
    User getUserById(String id);
    User getUserByUsernameAndPassword(String username, String password);
}
