package com.example.demo.core;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

final public class RandomExample {

    @Test
    final public void random(){

        final Random random = new Random();

        final int next = random.nextInt(10) + 1;
        int count = next;

        while (count-- > 0){

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("next : " + next);
                }
            });

            thread.start();
        }
    }

    @Test
    final public void secureRandom(){

        SecureRandom secureRandom = new SecureRandom();
        int next = secureRandom.nextInt(10) + 1;

        System.out.println("secure next : " + next);
    }


    @Test
    final public void randomInt(){

        IntStream.range(0, 20)
                .forEach(
                        value -> {
                            System.out.println("value : " + RandomInt.get(1, 4));
                        }
                );
    }


    @Test
    final public void randomValue(){

        IntStream.range(0, 20)
                .forEach(
                        value ->
                        {
                            System.out.println("value : " + new RandomIntStub().value(1, 10));
                        }
                );
    }
}
