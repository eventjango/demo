package com.example.demo.visitor;

import lombok.extern.java.Log;

@Log
public class BookVisitor implements Visitor {
    @Override
    public void visit(Visitable visitable) {

        Book book = typeCast(visitable, Book.class);

        log.info("book title : " + book.getTitle());
        log.info("book title : " + book.getPrice());
    }

    @Override
    public <T> T typeCast(Visitable visitable, Class<T> object) {
        T book = null;

        try {
            book = (T) visitable;
        }
        catch (ClassCastException e){

            e.printStackTrace();
        }

        return book;
    }
}
