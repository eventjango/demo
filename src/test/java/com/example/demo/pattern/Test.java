package com.example.demo.pattern;


import lombok.extern.java.Log;

@Log
public class Test {


    @org.junit.Test
    public void iterator(){

        Books books = new Books();
        books.add(new Book("Dragon Raja", 20000));
        books.add(new Book("Dragon Ball", 5000));


        Iterator booksIterator = books.iterator();

        while (booksIterator.hasNext()){
            Book book = (Book) booksIterator.next();
            log.info("book : " + book.toString());
        }

    }
}
