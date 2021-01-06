package com.example.shopA.service.Impls;

import com.example.shopA.model.AddOns;
import com.example.shopA.service.ServiceResult;
import com.example.shopA.model.Shop;
import com.example.shopA.repository.AddOnsRepository;
import com.example.shopA.service.AddOnService;
import com.example.shopA.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddOnServiceImpl implements AddOnService {
    private static final Logger logger = LoggerFactory.getLogger(AddOnServiceImpl.class);
    @Autowired
    private ShopService shopService;

    @Autowired
    private AddOnsRepository addOnsRepository;

    @Override
    public ServiceResult<Boolean> sendNotification(AddOns addOns, String shopId) {
        ServiceResult<Shop> shop = shopService.getShopById(shopId);

        if (shop.getCode() == 0 && !shop.getResult().getSubscribedAddons().contains(addOns)) {
            logger.info(addOns.getAddonName() + " is activated");
            return new ServiceResult<>(0, "add on activated", true );
        }

        return new ServiceResult<>(-1, "addon already activated", false);
    }

    @Override
    public ServiceResult<List<AddOns>> getAllAddOns() {
        List<AddOns> allAddOns = addOnsRepository.findAll();

        if (allAddOns == null && allAddOns.isEmpty()) {
            return new ServiceResult<>(-1, "no addons found");
        }

        return new ServiceResult<>(0, "available addons", allAddOns);
    }

    @Override
    public ServiceResult<AddOns> getAddOnById(String addOnId) {
        Optional<AddOns> addOn = addOnsRepository.findById(addOnId);

        if (!addOn.isPresent()) {
            return new ServiceResult<>(-1, "addon not found with id: " + addOnId);
        }

        return new ServiceResult<>(0, "addon found with id: " + addOnId, addOn.get());
    }
}
