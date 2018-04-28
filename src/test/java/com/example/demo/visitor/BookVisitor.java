package com.example.demo.visitor;

import lombok.extern.java.Log;

@Log
public class BookVisitor implements Visitor {

    protected VisitableTypeCaster typeCaster;

    public BookVisitor(VisitableTypeCaster typeCaster){
        this.typeCaster = typeCaster;
    }

    @Override
    public void visit(Visitable visitable) {

        Book book = typeCaster.cast(visitable, Book.class);

        log.info("book title : " + book.getTitle());
        log.info("book title : " + book.getPrice());
    }

}
