package com.skypro.skyshop.model.basket;

import com.skypro.skyshop.model.product.Product;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem item = (BasketItem) o;
        return quantity == item.quantity && Objects.equals(product, item.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}
