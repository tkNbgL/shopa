package com.example.shopA.service;

import com.example.shopA.model.Shop;

public interface ShopService {
    ServiceResult<Shop> getShopById(String id);

    ServiceResult<Boolean> saveShop(Shop shop);

    ServiceResult<Boolean> deleteShop(String shopId);
}
