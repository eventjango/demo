package com.example.demo.visitor;

public class Test {


    private Visitor visitor;

    @org.junit.Test
    public void book(){

        Book book = Book.builder().title("Dragon-Raja").price(20000).build();

        visitor = new BookVisitor(new VisitableTypeCaster());
        visitor.visit(book);

        /*Books books = new Books();
        books.add(book);

        visitor = new BooksVisitor(new VisitableTypeCaster());
        visitor.visit(books);*/
    }

    @org.junit.Test
    public void books(){

        Books books = new Books();
        Book book = Book.builder().title("82년생 김지영").price(12000).build();

        books.add(book);

        visitor = new BooksVisitor(new VisitableTypeCaster());
        /*visitor.visit(books);*/
        books.accept(visitor);
    }

}
