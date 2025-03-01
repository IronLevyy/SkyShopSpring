package com.skypro.skyshop.model.error;

import com.skypro.skyshop.exception.NoSuchProductException;

public record ShopError(String code, String message) {
    public static ShopError fromNoSuchProductException(NoSuchProductException e) {
        return new ShopError("400", e.getMessage());
    }
}
