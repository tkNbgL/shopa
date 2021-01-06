package com.example.shopA.controller;

import com.example.shopA.payload.response.SuccessResponse;
import com.example.shopA.service.ServiceResult;
import com.example.shopA.model.Shop;
import com.example.shopA.payload.response.MessageResponse;
import com.example.shopA.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("api/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    @GetMapping("{shopId}")
    public ResponseEntity<?> findShopById(@PathVariable String shopId) {
        ServiceResult<Shop> shop = shopService.getShopById(shopId);

        if (shop.getCode() != 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no shop found"));
        }

        return ResponseEntity.ok(new SuccessResponse
                .SuccessResponseBuilder<Shop>(shop.getResult(), shop.getMessage())
                .setStatusCode("200").setTransactionDate(new Date()).build());
    }
}
