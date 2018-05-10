package com.example.demo.mediator;

public class Test {

    @org.junit.Test
    public void mediator(){

        TagMediator tagMediator = new TagMediator();

        tagMediator.createMembers();
        tagMediator.memberChanged();
    }

    @org.junit.Test
    public void mediatorSupport(){

        new TagMediator(TagMediator.TagMediatorSupport.class).memberChanged();
    }
}
