package com.example.demo.iterator;


import lombok.extern.java.Log;

@Log
public class Test {


    @org.junit.Test
    public void iterator(){

        Books books = new Books();
        books.add(Book.builder().title("Dragon Raja").price(20000).build());
        books.add(Book.builder().title("Dragon Ball").price(5000).build());

        Iterator booksIterator = books.iterator();

        while (booksIterator.hasNext()){
            Book book = (Book) booksIterator.next();
            log.info("book : " + book.toString());
        }
    }
}
