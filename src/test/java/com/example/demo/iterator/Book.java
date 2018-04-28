package com.example.demo.iterator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private String title;

    private int price;

    public Book(String title, int price){
        this.title = title;
        this.price = price;
    }
}
