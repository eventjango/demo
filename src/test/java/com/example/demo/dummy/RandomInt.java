package com.example.demo.dummy;

import java.util.Random;

public interface RandomInt {

    default int value(int from, int to){
        return RandomInt.get(from, to);
    };

    static int get(int from, int to){
        return new Random().nextInt(to - (from - 1)) + from;
    }
}

class RandomIntStub implements RandomInt
{}
