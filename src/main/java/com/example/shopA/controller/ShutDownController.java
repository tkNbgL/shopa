package com.example.shopA.controller;

import com.example.shopA.model.Shop;
import com.example.shopA.payload.response.SuccessResponse;
import com.example.shopA.service.ServiceResult;
import com.example.shopA.payload.response.MessageResponse;
import com.example.shopA.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/shutdown")
public class ShutDownController {
    @Autowired
    ShopService shopService;


    @DeleteMapping("{shopId}")
    public ResponseEntity<?> shutDownAccount(@PathVariable String shopId) {
        ServiceResult<Boolean> shop = shopService.deleteShop(shopId);
        if (shop.getCode() != 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: shop not found"));
        }

        return ResponseEntity.ok(new SuccessResponse
                .SuccessResponseBuilder<Boolean>(shop.getResult(), shop.getMessage())
                .setStatusCode("204").setTransactionDate(new Date()).build());
    }
}
