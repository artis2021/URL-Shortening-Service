package com.example.URLShorteningService.entity;

import jakarta.persistence.Entity;
import lombok.Getter;


public class ShortUrlRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
