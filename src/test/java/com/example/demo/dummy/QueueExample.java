package com.example.demo.dummy;

import static org.junit.Assert.*;

import lombok.extern.java.Log;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;


@Log
public class QueueExample {

    @Test
    public void add(){

        Queue<? super String> queue = new ArrayDeque<>(Arrays.asList("kevin", "jack", "may"));

        log.info("queue element : " + queue.element());
        log.info("queue peek : " + queue.peek());
        log.info("queue poll : " + queue.poll());

        queue.remove();
        queue.forEach(System.out::println);
    }
}
