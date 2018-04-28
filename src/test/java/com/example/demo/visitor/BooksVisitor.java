package com.example.demo.visitor;

public class BooksVisitor implements Visitor {

    protected VisitableTypeCaster typeCaster;

    public BooksVisitor(VisitableTypeCaster typeCaster){
        this.typeCaster = typeCaster;
    }

    @Override
    public void visit(Visitable visitable) {

        Books books = typeCaster.cast(visitable, Books.class);
        System.out.println("book : " + books.get());
    }

}
