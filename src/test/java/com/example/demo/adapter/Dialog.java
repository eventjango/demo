package com.example.demo.adapter;

public class Dialog {

    protected <T> void dialing(T t){
        System.out.println((t instanceof Button) ? "button click" : t.toString());
    }
}
