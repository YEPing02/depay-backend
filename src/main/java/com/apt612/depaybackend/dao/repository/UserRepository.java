package com.apt612.depaybackend.dao.repository;

import com.apt612.depaybackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    @Query(value="{'pseudo':?0}")
    List<User> findByPseudo(String pseudo);
}
