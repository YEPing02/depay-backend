package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.UserDao;
import com.apt612.depaybackend.exception.PseudoDupliException;
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
    public User create(User user) throws PseudoDupliException {
        if (user.getPseudo().equals("") || user.getPassword().equals("") || !isUniquePseudo(user.getPseudo())) {
            throw new PseudoDupliException(user.getPseudo());
        }
        return userDao.create(user);
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public User login(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    public Boolean isUniquePseudo(String pseudo) {
        return userDao.isUniqueName(pseudo);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }
}
