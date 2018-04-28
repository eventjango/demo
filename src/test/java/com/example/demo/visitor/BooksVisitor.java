package com.example.demo.visitor;

public class BooksVisitor implements Visitor {

    @Override
    public void visit(Visitable visitable) {

        Books books = typeCast(visitable, Books.class);
        System.out.println("book : " + books.get());
    }

    @Override
    public <T> T typeCast(Visitable visitable, Class<T> object) {
        T books = null;

        try {
            books = (T) visitable;

        }catch (ClassCastException e){
            e.printStackTrace();
        }

        return books;
    }
}
