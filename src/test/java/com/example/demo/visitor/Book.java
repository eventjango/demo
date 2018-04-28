package com.example.demo.visitor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book implements Visitable{

    private String title;

    private Integer price;

    public Book(){}

    public Book(String title, Integer price){
        this.title = title;
        this.price = price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
