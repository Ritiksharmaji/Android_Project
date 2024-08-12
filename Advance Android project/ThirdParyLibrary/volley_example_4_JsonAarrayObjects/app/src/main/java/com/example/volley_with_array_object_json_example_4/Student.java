package com.example.volley_with_array_object_json_example_4;

public class Student {
    private String name;
    private int age;
    private String sex;
    private String id;
    private String course;
    private String year;

    // Constructor
    public Student(String name, int age, String sex, String id, String course, String year) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.id = id;
        this.course = course;
        this.year = year;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSex() { return sex; }
    public String getId() { return id; }
    public String getCourse() { return course; }
    public String getYear() { return year; }
}
