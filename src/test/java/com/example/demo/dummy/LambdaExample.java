package com.example.demo.dummy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class LambdaExample {

    @Test
    public void comparator(){

        String[] strings = {"kevin", "jack", "may"};

        Comparator<String> comparator = (a, b) -> a.length() - b.length();
        Arrays.sort(strings, comparator.reversed());
    }

    @Test
    public void runnable(){

        class Repeat {

            void repeat(int count, Runnable action){

                IntStream.range(1, count--).forEach(value -> action.run());

                /*while (count-- > 0){
                    action.run();
                }*/
            }
        }

        new Repeat().repeat(10, () -> System.out.println("fuck action"));
    }

}
