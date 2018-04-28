package com.example.demo.visitor;

import lombok.extern.java.Log;

@Log
public class BookVisitor implements Visitor {
    @Override
    public void visit(Visitable visitable) {

        log.info("book title : " + cast(visitable, Book.class).getTitle());
        log.info("book title : " + cast(visitable, Book.class).getPrice());
    }

    @Override
    public <T> T cast(Visitable visitable, Class<T> object) {
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
