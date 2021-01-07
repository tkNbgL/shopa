package com.example.shopA;

import com.example.shopA.model.AddOns;
import com.example.shopA.model.Shop;
import com.example.shopA.repository.AddOnsRepository;
import com.example.shopA.repository.ShopRepository;
import com.example.shopA.service.Impls.AddOnServiceImpl;
import com.example.shopA.service.Impls.ShopServiceImpl;
import com.example.shopA.service.ServiceResult;
import com.example.shopA.service.ShopService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddOnServiceTest {
    @InjectMocks
    AddOnServiceImpl addOnService;
    @Mock
    ShopServiceImpl shopService;

    AddOns addOn;
    Shop shop;
    ServiceResult<Shop> shopServiceResult;

    @Before
    public void setup() {
        addOn = new AddOns("1", "Google Analytics");
        List<AddOns> addOnList = new ArrayList<>();
        addOnList.add(addOn);
        shop = new Shop("1", "utkushop", addOnList);
        shopServiceResult = new ServiceResult<>(0, "add on activated", shop);
    }

    @Test
    public void whenAddonActivated_sendNotification() {
        AddOns addOn = new AddOns("2", "Chatmate");
        Mockito.when(shopService.getShopById("1")).thenReturn(shopServiceResult);
        ServiceResult<Boolean> sendNotificationResult = addOnService.sendNotification(addOn, shop.getId());
        Assert.assertEquals(sendNotificationResult.getResult(), true);
    }
}
