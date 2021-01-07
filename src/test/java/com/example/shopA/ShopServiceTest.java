package com.example.shopA;

import com.example.shopA.model.AddOns;
import com.example.shopA.model.Shop;
import com.example.shopA.repository.ShopRepository;
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
public class ShopServiceTest {
    @Mock
    ShopRepository shopRepository;
    @InjectMocks
    ShopServiceImpl shopService;
    Shop shop;
    AddOns addOn;

    @Before
    public void setup(){
        addOn = new AddOns("1", "Google Analytics");
        List<AddOns> addOnList = new ArrayList<>();
        addOnList.add(addOn);
        shop = new Shop("1", "utkushop", addOnList);
    }

    @Test
    public void whenFindById_thenReturnShop() {
        Mockito.when(shopRepository.findById("1")).thenReturn(Optional.of(shop));
        ServiceResult<Shop> shopById = shopService.getShopById("1");
        Assert.assertEquals(shopById.getResult(), shop);
    }

    @Test
    public void whenSaveShop_thenReturnTrue() {
        Mockito.when(shopRepository.save(shop)).thenReturn(shop);
        ServiceResult<Boolean> shopServiceResult = shopService.saveShop(shop);
        Assert.assertEquals(shopServiceResult.getResult(), true);
    }

}
