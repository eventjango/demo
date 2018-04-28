package com.example.demo.pattern;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;

@Data
public class Book {

    private String title;

    private int price;

    public Book(String title, int price){
        this.title = title;
        this.price = price;
    }
}
