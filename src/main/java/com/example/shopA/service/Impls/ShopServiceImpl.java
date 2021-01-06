package com.example.shopA.service.Impls;

import com.example.shopA.service.ServiceResult;
import com.example.shopA.model.Shop;
import com.example.shopA.repository.ShopRepository;
import com.example.shopA.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;


    @Override
    public ServiceResult<Shop> getShopById(String id) {
        Optional<Shop> shop = shopRepository.findById(id);

        if (!shop.isPresent()) {
            return new ServiceResult<>(-1, "shop not found");
        }

        return new ServiceResult<>(0, shop.get().getShopName(), shop.get());
    }
    
    public ServiceResult<Boolean> saveShop(Shop shop) {
        Shop savedShop = shopRepository.save(shop);

        if (savedShop == null) {
            return new ServiceResult<>(-1, "document not saved successfully");
        }

        return new ServiceResult<>(0, "document saved successfully", true);
    }

    @Override
    public ServiceResult<Boolean> deleteShop(String shopId) {
        ServiceResult<Shop> shopById = getShopById(shopId);

        if (shopById.getCode() != 0) {
            return new ServiceResult<>(-1, shopById.getMessage(), false);
        }

        shopRepository.delete(shopById.getResult());
        return new ServiceResult<>(0, "Shop with id: " + shopId + "deleted", true);
    }
}
