package com.example.demo.dummy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class LambdaExample {

    @Test
    public void comparator(){

        String[] strings = {"kevin", "jack", "may"};

        Comparator<String> comparator = (a, b) -> a.length() - b.length();
        Arrays.sort(strings, comparator.reversed());
        Arrays.sort(strings, Comparator.comparing(String::length, Comparator.nullsFirst(Comparator.reverseOrder())));

        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void runnable(){

        class Repeat {

            void repeat(int count, Runnable action){

                IntStream.range(1, count--).forEach(value -> action.run());

                /*while (count-- > 0){
                    action.run();
                }*/
            }
        }

        new Repeat().repeat(10, () -> System.out.println("fuck action"));
    }


    @Test
    public void closure(){

        class RepeatMessenger{

            //--- text와 count는 free value (effectively final)
            // 파라미터와 메서드 호출 스택이 사라져도 람다가 캡처한다
            void repeatMessage(String text, int count){

                /*Runnable runnable = () ->
                {
                    IntStream.range(1, count)
                            .forEach(
                                    value -> System.out.println("text : " + text)
                            );
                };*/
                Runnable action = () -> System.out.println("text : " + text);

                IntStream.range(1, count).forEach(value -> new Thread(action).start());

                /*new Thread(runnable).start();*/
            }
        }

        //-- 람다 안에서 값이 변하면 안되고 값이 변하는 만큼 람다를 돌려야 한다.

        new RepeatMessenger().repeatMessage("fucking!", 10);
    }

    @Test
    public void closureAndCallback(){

        new RepeatTemplate("fuck", 5).applyCallback(

                new RepeatMessenger() {

                    int count;

                    @Override
                    public void repeatMessage(String text, int count) {

                        this.count = count;

                        Runnable runnable = () ->
                        {
                            while (this.count-- > 0){
                                System.out.println("text : " + text);
                            }
                        };

                        new Thread(runnable).start();
                    }
                }
        );
    }
}


interface RepeatMessenger {

    void repeatMessage(String text, int count);
}


class RepeatTemplate
{
    String text;
    int count;


    RepeatTemplate(String text, int count){
        this.text = text;
        this.count = count;
    }

    void applyCallback(RepeatMessenger repeatMessenger){
        repeatMessenger.repeatMessage(text, count);
    }
}
