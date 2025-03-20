package com.skypro.skyshop.model.basket;


import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }
}
