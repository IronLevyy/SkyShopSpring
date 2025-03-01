package com.skypro.skyshop.controller;

import com.skypro.skyshop.exception.NoSuchProductException;
import com.skypro.skyshop.model.error.ShopError;
import com.skypro.skyshop.service.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ShopControllerAdvice {
    public final BasketService basketService;

    public ShopControllerAdvice(BasketService basketService) {
        this.basketService = basketService;
    }

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        return ResponseEntity.badRequest().body(ShopError.fromNoSuchProductException(e));
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id){
        return basketService.putProductInBasket(id);
    }
}
