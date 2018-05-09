package com.example.demo.iterator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * book을 가질 iterable의 역할
 */
@Data
public class Books implements Iterable {


    /**
     * books를 순회할 역할
     */
    class BooksIterator implements Iterator{

        // has
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


    /**
     * 자기에 대한 이터레이터를 생성할 책임
     * @return
     */

    // create
    @Override
    public Iterator iterator() {
        return new BooksIterator(this);
    }
}

