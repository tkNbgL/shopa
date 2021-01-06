package com.example.shopA.service;

import com.example.shopA.model.AddOns;

import java.util.List;

public interface AddOnService {
    ServiceResult<Boolean> sendNotification(AddOns andOns, String shopId);

    ServiceResult<List<AddOns>> getAllAddOns();

    ServiceResult<AddOns> getAddOnById(String addOnId);
}
