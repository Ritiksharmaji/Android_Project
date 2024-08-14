package com.example.news;

public class Admin {
    private String name;
    private String email;

    public Admin() {
        // Default constructor required for Firestore serialization
    }

    public Admin(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
