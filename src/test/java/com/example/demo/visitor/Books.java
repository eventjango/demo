package com.example.demo.visitor;


import lombok.Data;

import java.util.Stack;

@Data
public class Books implements Visitable{

    private Stack<Book> books = new Stack<>();

    public void add(Book book){
        books.addElement(book);
    }

    public Book get(){
        return books.pop();
    }

    public Integer count(){
        return books.size();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
