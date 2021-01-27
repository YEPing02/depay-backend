package com.apt612.depaybackend.dao.repository;

import com.apt612.depaybackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User getByPseudoAndPassword(String pseudo, String password);
}
