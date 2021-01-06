package com.example.shopA.controller;

import com.example.shopA.model.AddOns;
import com.example.shopA.payload.response.SuccessResponse;
import com.example.shopA.service.ServiceResult;
import com.example.shopA.model.Shop;
import com.example.shopA.payload.response.MessageResponse;
import com.example.shopA.service.AddOnService;
import com.example.shopA.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/addons")
public class AddOnController {
    @Autowired
    ShopService shopService;
    @Autowired
    AddOnService addOnService;

    @GetMapping("")
    public ResponseEntity getAllAddOns() {
        ServiceResult<List<AddOns>> allAddons = addOnService.getAllAddOns();

        if (allAddons.getCode() != 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no addon found"));
        }

        return ResponseEntity.ok(new SuccessResponse
                .SuccessResponseBuilder<List<AddOns>>(allAddons.getResult(), allAddons.getMessage())
                .setStatusCode("200").setTransactionDate(new Date()).build());
    }

    @GetMapping("{shopId}")
    public ResponseEntity<?> getActiveAddOn(@PathVariable String shopId) {
        ServiceResult<Shop> shop = shopService.getShopById(shopId);

        if (shop.getCode() != 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no shop found"));
        }

        return ResponseEntity.ok(new SuccessResponse
                .SuccessResponseBuilder<List<AddOns>>(shop.getResult().getSubscribedAddons(), shop.getMessage())
                .setStatusCode("200").setTransactionDate(new Date()).build());
    }

    @PostMapping("{shopId}/{addonId}")
    public ResponseEntity<?> activateAddOn(@PathVariable String shopId, @PathVariable String addonId) {
        ServiceResult<Shop> shop = shopService.getShopById(shopId);

        if (shop.getCode() != 0) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no shop found"));
        }

        ServiceResult<AddOns> addOns = addOnService.getAddOnById(addonId);

        if (addOns.getCode() != 0) {
            //TODO error json at
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no addon found"));
        }

        List<AddOns> subscribedAddons = shop.getResult().getSubscribedAddons();

        if (subscribedAddons.stream().anyMatch(i -> addOns.getResult().getId().equals(i.getId()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: addon already activated"));
        }
        AddOns activatedAddon = addOns.getResult();
        shop.getResult().addAddOnToList(activatedAddon);

        addOnService.sendNotification(activatedAddon, shopId);
        ServiceResult<Boolean> booleanServiceResult = shopService.saveShop(shop.getResult());

        return ResponseEntity.ok(new SuccessResponse
                .SuccessResponseBuilder<Boolean>(booleanServiceResult.getResult(), booleanServiceResult.getMessage())
                .setStatusCode("202").setTransactionDate(new Date()).build());
    }
}
