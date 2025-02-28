package com.skypro.skyshop.model.basket;

import com.skypro.skyshop.model.product.Product;

import java.util.List;

public final class UserBasket {
    private List<BasketItem> items;
    private int total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = countTotal();
    }

    private int countTotal() {
        return items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public int getTotal() {
        return total;
    }

    public List<BasketItem> getItems() {
        return items;
    }
}
