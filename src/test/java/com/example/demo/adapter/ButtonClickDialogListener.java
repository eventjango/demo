package com.example.demo.adapter;

public class ButtonClickDialogListener implements ButtonClickListener {

    @Override
    public void buttonPressed(Button button) {

        new Dialog().dialing(button);
    }
}
