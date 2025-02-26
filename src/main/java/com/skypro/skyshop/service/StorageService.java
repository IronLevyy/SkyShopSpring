package com.skypro.skyshop.service;

import com.skypro.skyshop.model.article.Article;
import com.skypro.skyshop.model.product.DiscountProduct;
import com.skypro.skyshop.model.product.FixPriceProduct;
import com.skypro.skyshop.model.product.Product;
import com.skypro.skyshop.model.product.SimpleProduct;
import com.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;
    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        getStorageMaps(products,articles);
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    private static void getStorageMaps(Map<UUID, Product> products, Map<UUID, Article> articles) {
        safelyCreateProduct("Bread",70, UUID.randomUUID(), products);
        safelyCreateProduct("Eggs", 120, UUID.randomUUID(), products);
        safelyCreateProduct("Milk", UUID.randomUUID(), products);
        safelyCreateProduct("Молоко",200,50, UUID.randomUUID(), products);
        safelyCreateProduct("Банан",90, UUID.randomUUID(), products);
        safelyCreateArticle("Первая статья", "Это моя Первая статья", UUID.randomUUID(), articles);
        safelyCreateArticle("Первая статья", "Это моя Первая статья", UUID.randomUUID(), articles);
        safelyCreateArticle("Вторая статья", "Текст", UUID.randomUUID(), articles);
    }


    public Collection<Searchable> getSearchables() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(this.products.values());
        searchables.addAll(this.articles.values());
        return searchables;
    }



    private static void safelyCreateProduct(String name, int price, int discount, UUID id, Map<UUID, Product> products) {
        try {
            products.put(id,new DiscountProduct(name, price, discount, id));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка " + e.getMessage());
        }
    }
    private static void safelyCreateProduct(String name, int price, UUID id, Map<UUID, Product> products) {
        try {
            products.put(id,new SimpleProduct(name, price, id));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка " + e.getMessage());
        }
    }
    private static void safelyCreateProduct(String name, UUID id, Map<UUID, Product> products) {
        try {
            products.put(id,new FixPriceProduct(name, id));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка " + e.getMessage());
        }
    }
    private static void safelyCreateArticle(String title, String body, UUID id, Map<UUID, Article> articles) {
        try {
            articles.put(id,new Article(title, body, id));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка " + e.getMessage());
        }
    }
}
