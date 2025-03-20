package com.skypro.skyshop.service;

import com.skypro.skyshop.model.search.SearchResult;
import com.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SearchService {
    private final StorageService storageService;
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String name) {
        Collection<Searchable> searchables = storageService.getSearchables();
        List<SearchResult> results = searchables.stream()
                .filter(searchable -> searchable.getSearchName().toLowerCase().contains(name.toLowerCase()))
                .map(SearchResult::fromSearcheble)
                .toList();
        return results;
    }
}
