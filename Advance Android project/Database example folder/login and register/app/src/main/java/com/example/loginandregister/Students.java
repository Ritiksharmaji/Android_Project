package com.example.loginandregister;

public class Students {

    String name, age, userId;

    public Students(String name, String age, String userId) {
        this.name = name;
        this.age = age;
        this.userId = userId;
    }

    public Students() {
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
