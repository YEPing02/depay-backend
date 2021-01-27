package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.UserDao;
import com.apt612.depaybackend.model.User;
import com.apt612.depaybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;
@Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
       return userDao.create(user);
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public User login(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username,password);
    }
}
