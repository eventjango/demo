package com.example.demo.visitor;

public class BooksVisitor implements Visitor {

    @Override
    public void visit(Visitable visitable) {
        cast(visitable, Books.class)
                .getBooks().stream()
                .forEach(System.out::println);
    }

    @Override
    public <T> T cast(Visitable visitable, Class<T> object) {
        T books = null;

        try {
            books = (T) visitable;

        }catch (ClassCastException e){
            e.printStackTrace();
        }

        return books;
    }
}
