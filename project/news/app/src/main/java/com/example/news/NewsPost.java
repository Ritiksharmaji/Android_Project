package com.example.news;

public class NewsPost {

    String title, content, userId;

    public NewsPost(String content, String title, String userId) {
        this.content = content;
        this.title = title;
        this.userId = userId;
    }

    public NewsPost() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
