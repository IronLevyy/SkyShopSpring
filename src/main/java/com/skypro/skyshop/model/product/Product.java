package com.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID Id;

    public Product(String name, UUID id) {
        Id = id;
        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым");
        }
        this.name = name;
    }

    @Override
    public UUID getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String searchTerm() {
        return name;
    }

    @Override
    @JsonIgnore
    public String getSearchName() {
        return name;
    }

    @Override
    @JsonIgnore
    public String getSearchType() {
        return "Product";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}