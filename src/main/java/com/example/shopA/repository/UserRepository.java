package com.example.shopA.repository;

import com.example.shopA.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByShopName(String username);
    Optional<User> findByMailAddress(String mailAddress);

}


