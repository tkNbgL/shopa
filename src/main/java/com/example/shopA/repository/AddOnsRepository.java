package com.example.shopA.repository;

import com.example.shopA.model.AddOns;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AddOnsRepository extends MongoRepository<AddOns, String> {
    Optional<AddOns> findByAddonName(String addonName);
}
