package com.skypro.skyshop.controller;

import com.skypro.skyshop.model.article.Article;
import com.skypro.skyshop.model.basket.UserBasket;
import com.skypro.skyshop.model.product.Product;
import com.skypro.skyshop.model.search.SearchResult;
import com.skypro.skyshop.service.BasketService;
import com.skypro.skyshop.service.SearchService;
import com.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    public final BasketService basketService;

    public ShopController(StorageService storageService , SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> searchProduct(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket(){
        return basketService.getUserBasket();
    }
}
