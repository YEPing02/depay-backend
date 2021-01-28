package com.apt612.depaybackend.dao;

import com.apt612.depaybackend.model.User;

public interface UserDao {
    User create(User user);
    User getUserById(String id);
    boolean isUniqueName (String name);
    User getUserByUsernameAndPassword(String username, String password);
    User update(User user);
}
