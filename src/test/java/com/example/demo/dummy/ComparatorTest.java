package com.example.demo.dummy;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.springframework.scheduling.config.Task;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class ComparatorTest {

    @Test
    public void stringOrder(){

        String[] strings = {"kevin", "jack", "may"};

        Comparator<String> comparator = (a, b) -> a.length() - b.length();

        Arrays.sort(strings, comparator.reversed());
    }
}
