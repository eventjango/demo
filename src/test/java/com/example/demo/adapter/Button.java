package com.example.demo.adapter;

public class Button {

    protected ButtonClickListener buttonClickListener;

    Button(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    void click(){
        buttonClickListener.buttonPressed(this);
    }
}
