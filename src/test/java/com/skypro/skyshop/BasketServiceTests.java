package com.skypro.skyshop;

import com.skypro.skyshop.exception.NoSuchProductException;
import com.skypro.skyshop.model.basket.BasketItem;
import com.skypro.skyshop.model.basket.ProductBasket;
import com.skypro.skyshop.model.basket.UserBasket;
import com.skypro.skyshop.model.product.SimpleProduct;
import com.skypro.skyshop.service.BasketService;
import com.skypro.skyshop.service.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTests {
    @Mock
    ProductBasket productBasket;
    @Mock
    StorageService storageService;
    @InjectMocks
    BasketService basketService;

    @Test
    void givenDoesntExistProduct_whenEmptyBasket_thenThrowsException() {
        UUID idDoesntExistProduct = UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae1");
        Mockito.when(storageService.getProductById(idDoesntExistProduct)).thenReturn(Optional.empty());

        NoSuchProductException exception = Assertions.assertThrows(NoSuchProductException.class, ()->{
            basketService.putProductInBasket(idDoesntExistProduct);
        });
        Assertions.assertEquals(exception.getMessage(), "Продукт не найден");
    }

    @Test
    void givenExistProduct_whenEmptyBasket_thenPassed() {
        SimpleProduct testProduct = new SimpleProduct("Молоко", 50, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae1"));
        Mockito.when(storageService.getProductById(testProduct.getId())).thenReturn(Optional.of(testProduct));

        Assertions.assertEquals("Продукт успешно добавлен", basketService.putProductInBasket(testProduct.getId()));
        Mockito.verify(productBasket, Mockito.times(1)).putProduct(testProduct.getId());
    }

    @Test
    void givenEmptyProductBasket_whenGetUserBasket_thenEmptyList() {
        List<BasketItem> items = new ArrayList<>();
        UserBasket emptyUserBasket = new UserBasket(items);
        Assertions.assertEquals(emptyUserBasket, basketService.getUserBasket());
    }

    @Test
    void givenExistProductBasket_whenGetUserBasket_thenBasketList() {
        SimpleProduct product1 = new SimpleProduct("Молоко", 50, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae1"));
        SimpleProduct product2 = new SimpleProduct("Яйца", 80, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae2"));
        SimpleProduct product3 = new SimpleProduct("Хлеб", 20, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae3"));
        BasketItem item1 = new BasketItem(product1, 1);
        BasketItem item2 = new BasketItem(product2, 1);
        BasketItem item3 = new BasketItem(product3, 1);
        List<BasketItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        UserBasket testUserBasket = new UserBasket(items);
        Map<UUID, Integer> basket = new HashMap<>();
        basket.put(item1.getProduct().getId(), item1.getQuantity());
        basket.put(item2.getProduct().getId(), item2.getQuantity());
        basket.put(item3.getProduct().getId(), item3.getQuantity());

        Mockito.when(productBasket.getBasket()).thenReturn(Collections.unmodifiableMap(basket));
        Mockito.when(storageService.getProductById(item1.getProduct().getId())).thenReturn(Optional.of(product1));
        Mockito.when(storageService.getProductById(item2.getProduct().getId())).thenReturn(Optional.of(product2));
        Mockito.when(storageService.getProductById(item3.getProduct().getId())).thenReturn(Optional.of(product3));

        Assertions.assertEquals(testUserBasket, basketService.getUserBasket());
    }

}
