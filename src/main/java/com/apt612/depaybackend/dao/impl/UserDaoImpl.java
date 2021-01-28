package com.apt612.depaybackend.dao.impl;

import com.apt612.depaybackend.dao.UserDao;
import com.apt612.depaybackend.dao.repository.UserRepository;
import com.apt612.depaybackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getByPseudoAndPassword(username, password);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    public boolean isUniqueName(String name) {
        return userRepository.findByPseudo(name).size() == 0;
    }

}
