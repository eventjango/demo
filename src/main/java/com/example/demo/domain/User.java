package com.example.demo.domain;


import lombok.Data;

@Data
public class User {

    private long id;
    private String name;
    private int age;
    private double salary;

    private User(){}

    public User(long id, String name, int age, double salary){
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
