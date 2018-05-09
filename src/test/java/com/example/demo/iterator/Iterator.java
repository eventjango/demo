package com.example.demo.iterator;


/**
 * Iterable에 접근해서 요소를 순회하는 역할
 */
public interface Iterator {


    /**
     * 다음 요소의 존재여부를 확인하는 책임
     * @return
     */
    boolean hasNext();

    /**
     * 다음 요소를 리턴하며 시퀀싱하는 책임
     * @return
     */
    Object next();
}
