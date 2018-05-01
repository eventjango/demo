package com.example.demo.dummy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringExample {

    @Test
    public void join(){

        String result = String.join("-", "kevin", "jack", "may");

        result = String.join(", ", Arrays.asList("kevin", "jack", "may"));

        System.out.println("result : " + result);
    }

    @Test
    public void split(){

        String joinString = String.join(StringUtils.SPACE, Arrays.asList("kevin", "jack", "may"));
        System.out.println("joinString : " + joinString);

        String[] result = joinString.split("\\s+");

        Arrays.asList(result)
                .forEach(System.out::println);

    }
}
