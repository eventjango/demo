package com.example.demo.dummy;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;

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
}
