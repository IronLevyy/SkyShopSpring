package com.skypro.skyshop.model.basket;


import java.util.*;


public class ProductBasket {
    private final Map<UUID, Integer> basket = new HashMap<>();

    public void putProduct(UUID productId) {
        basket.put(productId, basket.getOrDefault(productId, 0) + 1);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }

}
