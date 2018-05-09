package com.example.demo.iterator;


/**
 * 이터레이터를 생성하는 역할
 */
public interface Iterable {

    /**
     * 이터레이터를 생성하는 책임
     * @return
     */
    Iterator iterator();
}
