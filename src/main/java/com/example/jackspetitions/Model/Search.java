package com.example.jackspetitions.Model;

import java.io.Serializable;

public class Search implements Serializable {
    private String search;
    public Search() {
    }
    public void setSearch(String search) {
        this.search = search;
    }
    public String getSearch() {
        return search;
    }

}
