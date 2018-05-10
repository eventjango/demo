package com.example.demo.mediator;


/**
 * 다수의 멤버들로부터 보고를 받고 지시를 내리는 역할
 */
public interface Mediator {

    //-- 1. 멤버들을 생성할 책임
    void createMembers();

    //-- 2. 멤버들에게 상담이나 보고를 받을 책임
    void memberChanged();
}
