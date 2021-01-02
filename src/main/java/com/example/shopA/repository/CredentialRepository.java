package com.example.shopA.repository;

import com.example.shopA.model.Credentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends MongoRepository<Credentials, String> {
    Optional<Credentials> findByMailAddress(String mailAddress);
}
