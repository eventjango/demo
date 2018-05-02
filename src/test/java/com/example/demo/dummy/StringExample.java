package com.example.demo.dummy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void stringBuilder(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("kevin");

        exit:
        {

            while(true){

                System.out.println("todo fuck");
                break exit;
            }

        }
        System.out.println(stringBuilder.toString());
    }
}
