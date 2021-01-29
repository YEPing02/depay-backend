package com.apt612.depaybackend.service.impl;

import com.apt612.depaybackend.dao.UserDao;
import com.apt612.depaybackend.exception.InvalidDataException;
import com.apt612.depaybackend.exception.PseudoDupliException;
import com.apt612.depaybackend.model.User;
import com.apt612.depaybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) throws PseudoDupliException, InvalidDataException {
        if (verifyPseudo(user)) {
            if(verifyPassword(user)){
                if(isUniquePseudo(user.getPseudo())){
                    return userDao.create(user);
                }
                throw new PseudoDupliException(user.getPseudo());
            }
            throw new InvalidDataException("Password");
        }
        throw new InvalidDataException("Pseudo");
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public User login(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public Boolean isUniquePseudo(String pseudo) {
        return userDao.isUniqueName(pseudo);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

     private boolean verifyPseudo(User user){
        String pseudoRegex="^[a-zA-Z][a-zA-Z0-9_]{4,15}$";

        return user.getPseudo() !=null
                && !user.getPseudo().equals("")
                && Pattern.matches(pseudoRegex,user.getPseudo());
    }

    private  boolean verifyPassword(User user){
        String passwodRegex = "(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}";
        return user.getPassword() !=null
                && !user.getPassword().equals("")
                && Pattern.matches(passwodRegex,user.getPassword());
    }

}
