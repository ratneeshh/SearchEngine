package com.ratnesh.web_crawler.model;

import java.util.List;

public class Indexing {
    private String url;
    private String title;
    private String description;
    private List<String> words;

    public Indexing(String url, String title, String description, List<String> words) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.words = words;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
