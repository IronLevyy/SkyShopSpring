package com.skypro.skyshop.service;

import com.skypro.skyshop.exception.NoSuchProductException;
import com.skypro.skyshop.model.basket.BasketItem;
import com.skypro.skyshop.model.basket.ProductBasket;
import com.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
        private final ProductBasket productBasket;
        private final StorageService storageService;

        public BasketService(ProductBasket productBasket, StorageService storageService) {
            this.productBasket = productBasket;
            this.storageService = storageService;
        }

        public String putProductInBasket(UUID productId) {
            if (storageService.getProductById(productId).isPresent()) {
                productBasket.putProduct(productId);
                return "Продукт успешно добавлен";
            }else{
                throw new NoSuchProductException();
            }
        }

        public UserBasket getUserBasket() {
            return new UserBasket (productBasket.getBasket().entrySet().stream()
                    .map(entry-> new BasketItem(storageService.getProductById(entry.getKey()).get(), entry.getValue()))
                    .toList());
        }
}
