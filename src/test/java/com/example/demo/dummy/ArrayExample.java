package com.example.demo.dummy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArrayExample {

    @Test
    public void literal(){

        String[] array = {"kevin", "jack", "may"};

        array = new String[]{"kevin", "jack", "may", "me"};
        System.out.println(Arrays.toString(array));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void empty(){

        int[] array = new int[0];

        array = new int[]{};
        array[0] = 0;

        System.out.println(Arrays.toString(array));

    }


    /**
     * Arrays.copyOf(target, size) : Target[]
     */
    @Test
    public void copy(){

        Object[] array =
        Arrays.asList("kevin", "jack", "may")
                .toArray();

        String[] result = (String[]) Arrays.copyOf(array, array.length);
        System.out.println("result : " + Arrays.toString(result));

        new ArrayList<>(Arrays.asList(result))
                .stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }


    /**
     * Arrays.sort(target, comparator) : void
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void fillAndSort(){

        String[] array = {"kevin", "jack", "may"};
        Arrays.sort(array, Comparator.comparing(String::length, Comparator.naturalOrder()));

        System.out.println("sort : " + Arrays.toString(array));


        String[] fill = {"kevin"};
        Arrays.fill(fill, "jack");

        fill[0] = "may";
        fill[1] = "me";

        System.out.println("fill : " + Arrays.toString(fill));
    }
}
