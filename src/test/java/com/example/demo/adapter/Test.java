package com.example.demo.adapter;

public class Test {

    @org.junit.Test
    public void adapter(){

        Button button = new Button(new ButtonClickDialogListener());
        button.click();

    }
}
