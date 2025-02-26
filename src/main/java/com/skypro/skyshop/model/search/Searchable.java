package com.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String searchTerm();
    String getSearchName();
    String getSearchType();
    UUID getId();
    default void getStringRepresentation(){
        System.out.println("Название: " + getSearchName() + "\nТип: " + getSearchType());
    }
    default int compareTo(Searchable other){
        return getSearchName().compareTo(other.getSearchName());
    }
}