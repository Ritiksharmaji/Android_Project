package com.example.add_views_dynamically;

public class Language {

    String name;
    String exp;

    public Language() {

    }
    public Language(String exp, String name) {
        this.exp = exp;
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
