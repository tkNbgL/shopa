package com.example.shopA.model;

import java.util.List;

public class Shop {
    private String id;
    private String shopName;
    private List<AddOns> subscribedAddons;

    public Shop() { }

    public Shop(String id, String shopName, List<AddOns> subscribedAddons) {
        this.id = id;
        this.shopName = shopName;
        this.subscribedAddons = subscribedAddons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<AddOns> getSubscribedAddons() {
        return subscribedAddons;
    }

    public void setSubscribedAddons(List<AddOns> subscribedAddons) {
        this.subscribedAddons = subscribedAddons;
    }

    public void addAddOnToList(AddOns addOns) {
        this.subscribedAddons.add(addOns);
    }
}
