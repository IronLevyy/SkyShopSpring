package com.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product{
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }
        this.price = price;

    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return this.getName() + ":" + this.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct product = (SimpleProduct) o;
        return price == product.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }
}
