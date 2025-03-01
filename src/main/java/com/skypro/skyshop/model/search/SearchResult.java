package com.skypro.skyshop.model.search;

import java.util.UUID;

public record SearchResult(UUID id, String name, String contentType) {

    public static SearchResult fromSearcheble(Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getSearchName(), searchable.getSearchType());
    }
}
