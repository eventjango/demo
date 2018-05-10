package com.example.demo.mediator;


/**
 * 다수의 객체들의 그룹으로 미디에이터에게 상담할 역할, 멤버는 미디에이터를 소유할 책임을 갖는다
 */
public interface Member {


    //-- 1. 미디에이터를 물어라
    void setMediator(Mediator mediator);


    //-- 2. 미디에이터에게 지시를 받아라
    void setMemberEnabled(boolean enabled);

}
