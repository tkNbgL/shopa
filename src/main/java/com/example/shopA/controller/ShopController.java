package com.example.shopA.controller;

import com.example.shopA.model.Shop;
import com.example.shopA.payload.response.MessageResponse;
import com.example.shopA.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/shop")
public class ShopController {
    @Autowired
    ShopRepository shopRepository;

    @GetMapping("{shopId}")
    public ResponseEntity<?> findShopById(@PathVariable String shopId) {
        Optional<Shop> shop = shopRepository.findById(shopId);

        if (!shop.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no shop found"));
        }

        return ResponseEntity.ok(shop);
    }
}
