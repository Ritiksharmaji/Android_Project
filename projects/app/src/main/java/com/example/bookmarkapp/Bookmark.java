package com.example.bookmarkapp;

public class Bookmark {
    private String url;
    private String role;

    public Bookmark(String url, String role) {
        this.url = url;
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public String getRole() {
        return role;
    }
}

