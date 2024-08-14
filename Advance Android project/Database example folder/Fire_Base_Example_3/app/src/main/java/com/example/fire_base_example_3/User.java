package com.example.fire_base_example_3;

public class User {
    public String name, phone, city, email, department, profileImageUrl, password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String phone, String city, String email, String department, String profileImageUrl, String password) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.email = email;
        this.department = department;
        this.profileImageUrl = profileImageUrl;
        this.password = password;
    }
}

