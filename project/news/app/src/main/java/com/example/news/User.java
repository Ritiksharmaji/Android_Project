package com.example.news;

public class User {

String name, email, phone, branch;

    public User(String branch, String email, String name, String phone) {
        this.branch = branch;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
    public User() {


    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
