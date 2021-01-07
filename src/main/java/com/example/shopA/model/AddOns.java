package com.example.shopA.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AddOns {
    @Id
    private String id;
    private String addonName;

    public AddOns() {

    }

    public AddOns(String id, String addonName) {
        this.id = id;
        this.addonName = addonName;
    }

    public AddOns(String addonName) {
        this.addonName = addonName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }
}
