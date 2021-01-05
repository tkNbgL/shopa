package com.example.shopA.service.Impls;

import com.example.shopA.model.AddOns;
import com.example.shopA.model.Shop;
import com.example.shopA.repository.ShopRepository;
import com.example.shopA.service.AddOnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddOnServiceImpl implements AddOnService {
    private static final Logger logger = LoggerFactory.getLogger(AddOnServiceImpl.class);
    @Autowired
    ShopRepository shopRepository;
    @Override
    public void sendNotification(AddOns addOns, String shopId) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        if (!shop.get().getSubscribedAddons().contains(addOns)) {
            logger.info(addOns.getAddonName() + " is activated");
        }
    }
}
