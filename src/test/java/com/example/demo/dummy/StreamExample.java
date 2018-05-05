package com.example.demo.dummy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamExample {


    /**
     * Stream.of(T... values) : Stream<T>
     */
    @Test
    public void create(){

        System.out.println("count : " + Stream.of("kevin", "jack", "may").count());
        System.out.println("count : " + Stream.of(Arrays.asList("kevin", "jack", "may")).count());

        Stream.of(Arrays.asList("kevin", "jack", "may"))
                .forEach(
                        list ->
                        {
                            list.stream()
                                    .forEach(n -> System.out.println(n));
                        }
                );

        Stream.of(Arrays.asList("kevin", "jack").toArray())
                .forEach(n -> System.out.println(n.toString().toUpperCase()));
    }

    @Test
    public void toArray(){

        String[] result =

        Arrays.asList("kevin", "jack")
                .stream()
                .toArray(value -> new String[value]);

        System.out.println(String.join(StringUtils.SPACE, result));
    }
}


