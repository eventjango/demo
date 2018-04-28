package com.example.demo.visitor;

public class Test {


    @org.junit.Test
    public void visitor(){

        Book book = Book.builder().title("Dragon-Raja").price(20000).build();

        Visitor visitor = new BookVisitor();
        visitor.visit(book);

        Books books = new Books();
        books.add(book);

        visitor = new BooksVisitor();
        visitor.visit(books);
    }
}
