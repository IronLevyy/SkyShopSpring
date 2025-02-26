package com.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String body;
    private final UUID Id;

    public Article(String title, String body, UUID id) {
        this.title = title;
        this.body = body;
        Id = id;
    }

    @Override
    public UUID getId() {
        return Id;
    }

    @Override
    public String toString() {
        return title + "\n" + body;
    }

    @Override
    @JsonIgnore
    public String searchTerm() {
        return title + " " + body;
    }

    @Override

    public String getSearchName() {
        return title;
    }

    @Override
    @JsonIgnore
    public String getSearchType() {
        return "Article";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
