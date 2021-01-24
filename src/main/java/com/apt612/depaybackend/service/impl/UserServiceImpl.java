package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.UserDao;
import com.apt612.depaybackend.model.User;
import com.apt612.depaybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User create(User user) {
       return userDao.create(user);
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public Boolean isUniquePseudo(String pseudo) {
        return  userDao.isUniqueName(pseudo);
    }
}
