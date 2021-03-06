package com.example.demo.core;

import static org.junit.Assert.*;

import lombok.extern.java.Log;
import org.junit.Test;

import java.util.*;


@Log
public class RestDataStructExample {

    @Test
    public void vector(){

        Vector<? super String> vector = new Vector<>(Arrays.asList("kevin", "jack", "may"));
        assertEquals(3, vector.size());

        Enumeration<String> enumeration = (Enumeration<String>) vector.elements();

        while (enumeration.hasMoreElements()){

            String value = enumeration.nextElement();
            System.out.println("value : " + value);
        }

        vector.clear();
        vector.addElement("kevin");
        vector.addAll(Arrays.asList("jack", "may"));

        for(int i=0; i< vector.size(); ++i){
            System.out.println(vector.elementAt(i));
        }
    }

    /**
     * 후입선출 last out
     */
    @Test
    public void stack(){

        Stack<? super String> stack = new Stack<>();

        stack.addAll(Arrays.asList("kevin", "jack", "may"));
        stack.push("me");

        assertEquals(4, stack.size());
        assertEquals("me", stack.pop());
        assertEquals(3, stack.size());

    }

    /**
     * 선입선출 first out
     */
    @Test
    public void queue(){

        Queue<? super String> queue = new ArrayDeque<>(Arrays.asList("kevin", "jack", "may"));

        log.info("queue element : " + queue.element());
        log.info("queue peek : " + queue.peek());
        log.info("queue poll : " + queue.poll());

        queue.remove();
        queue.forEach(System.out::println);
    }
}
