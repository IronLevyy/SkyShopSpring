package com.skypro.skyshop.model.basket;

import com.skypro.skyshop.model.product.Product;

import java.util.Optional;

public final class BasketItem {
    private Product product;
    private int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
