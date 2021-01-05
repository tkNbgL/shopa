package com.example.shopA.controller;

import com.example.shopA.model.AddOns;
import com.example.shopA.model.Shop;
import com.example.shopA.payload.response.MessageResponse;
import com.example.shopA.repository.AddOnsRepository;
import com.example.shopA.repository.ShopRepository;
import com.example.shopA.service.AddOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/addons")
public class AddOnController {
    @Autowired
    AddOnsRepository addOnsRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AddOnService addOnService;

    @GetMapping("")
    public ResponseEntity<?> getAllAddOns() {
        List<AddOns> allAddons = addOnsRepository.findAll();

        if (allAddons == null && allAddons.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no addon found"));
        }

        return ResponseEntity.ok(allAddons);
    }

    @GetMapping("{shopId}")
    public ResponseEntity<?> getActiveAddOn(@PathVariable String shopId) {
        Optional<Shop> shop = shopRepository.findById(shopId);

        if (!shop.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no shop found"));
        }

        return ResponseEntity.ok(shop.get().getSubscribedAddons());
    }

    @PostMapping("{shopId}/{addonId}")
    public ResponseEntity<?> activateAddOn(@PathVariable String shopId, @PathVariable String addonId) {
        Optional<Shop> shop = shopRepository.findById(shopId);

        if (!shop.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no shop found"));
        }

        Optional<AddOns> addOns = addOnsRepository.findById(addonId);

        if (!addOns.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no addon found"));
        }

        List<AddOns> subscribedAddons = shop.get().getSubscribedAddons();

        if (subscribedAddons.stream().anyMatch(i -> addOns.get().getId().equals(i.getId()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: addon already activated"));
        }
        AddOns activatedAddon = addOns.get();
        shop.get().addAddOnToList(activatedAddon);

        addOnService.sendNotification(activatedAddon, shopId);
        shopRepository.save(shop.get());

        return ResponseEntity.ok(new MessageResponse("saved successfully"));
    }
}
