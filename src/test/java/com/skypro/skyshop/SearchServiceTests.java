package com.skypro.skyshop;


import com.skypro.skyshop.model.product.SimpleProduct;
import com.skypro.skyshop.model.search.SearchResult;
import com.skypro.skyshop.model.search.Searchable;
import com.skypro.skyshop.service.SearchService;
import com.skypro.skyshop.service.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTests {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;
    @Test
    void givenEmptyStorageService_whenSearchProduct_thenReturnEmptyList() {
        Mockito.when(storageService.getSearchables()).thenReturn(Collections.emptyList());
        String searchName = "Молоко";

        Collection<SearchResult> result = searchService.search(searchName);
        Mockito.verify(storageService, Mockito.times(1)).getSearchables();

        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @Test
    void givenFullStorageService_whenSearchProductWhichDoesntExist_thenReturnEmptyList() {
        Collection<Searchable> searchables = createSearchables();

        Mockito.when(storageService.getSearchables()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.search("Машина");
        Mockito.verify(storageService, Mockito.times(1)).getSearchables();

        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @Test
    void givenFullStorageService_whenSearchProduct_thenReturnSearchebleList() {
        Collection<Searchable> searchables = createSearchables();
        List<SearchResult> searchResults = new ArrayList<>();
        SimpleProduct product = new SimpleProduct("Молоко", 50, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae1"));
        searchResults.add(SearchResult.fromSearcheble(product));

        Mockito.when(storageService.getSearchables()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.search("Молоко");
        Mockito.verify(storageService, Mockito.times(1)).getSearchables();

        Assertions.assertEquals(searchResults, result);
    }

    private Collection<Searchable> createSearchables() {
        Collection<Searchable> searchables = new ArrayList<>();
        SimpleProduct product1 = new SimpleProduct("Молоко", 50, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae1"));
        SimpleProduct product2 = new SimpleProduct("Хлеб", 20, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae2"));
        SimpleProduct product3 = new SimpleProduct("Яйца", 100, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae3"));
        SimpleProduct product4 = new SimpleProduct("Масло", 150, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae4"));
        SimpleProduct product5 = new SimpleProduct("Чай", 50, UUID.fromString("b5d63c9f-efec-4257-b21c-f33fffe13ae5"));
        searchables.add(product1);
        searchables.add(product2);
        searchables.add(product3);
        searchables.add(product4);
        searchables.add(product5);
        return searchables;
        }
    }

