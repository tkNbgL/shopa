package com.example.shopA.repository;

import com.example.shopA.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existByUsername(String username);

    Boolean existByEmail(String email);
}
