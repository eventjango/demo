package com.example.demo.pattern;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Books implements Iterable {

    class BooksIterator implements Iterator{

        Books books;

        int index;

        BooksIterator(Books books){
            this.books = books;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (index < books.count());
        }

        @Override
        public Object next() {
            return books.get(index++);
        }
    }

    private List<Book> bookList = new ArrayList<>();

    public void add(Book book){
        bookList.add(book);
    }

    public void remove(Book book){
        bookList.remove(book);
    }

    public Book get(int index){
        return bookList.get(index);
    }

    public int count(){
        return bookList.size();
    }

    @Override
    public Iterator iterator() {
        return new BooksIterator(this);
    }
}

