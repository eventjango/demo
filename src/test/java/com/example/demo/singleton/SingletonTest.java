package com.example.demo.singleton;

import org.junit.Test;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertEquals;


class Singleton {

    private Singleton()
    {}

    public static Singleton singleton(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
}


@Component
public class SingletonTest{


    @Test
    public void compare(){

        assertEquals(Singleton.singleton(), Singleton.singleton());
    }

}