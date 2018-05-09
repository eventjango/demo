package com.example.demo.iterator;



import lombok.Builder;
import lombok.Data;


/**
 * iterable이 가질 요소의 역할
 */
@Data
@Builder
public class Book {

    /**
     * 출력하기 위한 용도의 간단한 상태들
     */
    private String title;

    private int price;

    public Book(String title, int price){
        this.title = title;
        this.price = price;
    }
}
