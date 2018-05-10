package com.example.demo.mediator;

public class Button implements Member {

    private Mediator mediator;

    public Button()
    {}

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setMemberEnabled(boolean enabled) {

        System.out.println(this.getClass().getName() + " state : " + enabled);
    }
}
