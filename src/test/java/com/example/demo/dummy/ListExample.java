package com.example.demo.dummy;

import lombok.extern.java.Log;
import org.junit.Test;

import java.util.*;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@Log
public class ListExample {

    @Test
    public void addAt(){

        List<? super String> list = new ArrayList<>(Arrays.asList("kevin"));

        list.add("jack");
        list.add(list.size(), "may");

        assertEquals(3, list.size());
        assertTrue(list.containsAll(Arrays.asList("kevin", "jack", "may")));
    }

    @Test
    public void addAllAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(Arrays.asList("may"));

        assertEquals(2, lists.size());
    }


    @Test
    public void removeAt(){

        List<? super String> list = new ArrayList<>();
        assertTrue(list.addAll(Arrays.asList("kevin", "jack", "may")));

        assertEquals(3, list.size());
        assertEquals(list.remove(list.size() - 1), "may");
        assertEquals(2, list.size());
    }


    @Test
    public void removeAllAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(Arrays.asList("may"));

        assertEquals(2, lists.size());
        assertEquals(lists.remove(lists.size() - 1), Arrays.asList("may"));
        assertEquals(1, lists.size());
    }


    @Test
    public void indexOf(){

        List<? super String> list = Arrays.asList("kevin", "jack", "may");

        assertEquals(0, list.indexOf("kevin"));
        assertEquals(1, list.indexOf("jack"));
        assertEquals(2, list.indexOf("may"));
    }


    @Test
    public void indexOfAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(Arrays.asList("may"));

        assertEquals(2, lists.size());

        assertEquals(0, lists.indexOf(Arrays.asList("kevin", "jack")));
        assertEquals(1, lists.indexOf(Arrays.asList("may")));
        assertEquals(-1, lists.indexOf("may"));
    }


    @Test
    public void listIterator(){

        List<? extends String> list = Arrays.asList("kevin", "jack", "may");

        ListIterator<String> listIterator = (ListIterator<String>) list.listIterator();

        while (listIterator.hasNext()){

            String value = listIterator.next();

            if(value.startsWith("m")){

                listIterator.previous();
                listIterator.set(value.toUpperCase());
                value = listIterator.next();
            }

            log.info("value : " + value);
        }


        listIterator = (ListIterator<String>) list.listIterator();
        listIterator.forEachRemaining(string -> System.out.println("value : " + string));
    }


    @Test
    public void listIteratorAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(Arrays.asList("may"));

        assertEquals(2, lists.size());


        lists.forEach(

                list ->
                {

                    ListIterator<String> listIterator = (ListIterator<String>) ((List<? extends String>) list).listIterator();

                    while (listIterator.hasNext()){

                        String value = listIterator.next();

                        if(value.startsWith("k")){

                            listIterator.previous();
                            listIterator.set(value.toUpperCase());
                            value = listIterator.next();
                        }

                        log.info("value : " + value);
                    }

                }
        );


        lists.forEach(
                list ->
                {

                    ListIterator<? extends String> listIterator = ((List<? extends String>) list).listIterator();
                    listIterator.forEachRemaining(System.out::println);
                }
        );
    }


    @Test
    public void sort(){

        List<? extends String> list = Arrays.asList("kevin", "jack", "may");

        assertEquals(3, list.size());

        Comparator<? super String> comparator = Comparator.comparing(String::length, Comparator.naturalOrder());
        comparator = comparator.reversed();

        list.sort(comparator);
        list.forEach(System.out::println);
    }



    @Test
    public void sortAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(Arrays.asList("may", "me"));

        assertEquals(2, lists.size());

        lists.forEach(

                list ->
                {
                    ((List<? extends String>) list).sort(Comparator.comparing(String::length, Comparator.naturalOrder()));
                }
        );

        log.info("lists : " + lists.toString());


        ListIterator<? super List<? extends String>> listsIterator = lists.listIterator();

        while (listsIterator.hasNext()){

            List<? extends String> list = (List<? extends String>) listsIterator.next();

            if(lists.indexOf(list) == 0)
                list.sort(Comparator.comparing(String::length, Comparator.naturalOrder()));
            else if(lists.indexOf(list) == 1)
                list.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));

        }

        log.info("lists : " + lists.toString());

    }

    @Test
    public void replaceAll(){

        List<? super String> list = Arrays.asList("kevin", "jack", "may");

        list.replaceAll(string -> ((String) string).toUpperCase());
        list.sort(Comparator.comparing(string -> string.toString().length(), Comparator.naturalOrder()).reversed());

        list.forEach(System.out::println);
    }




}
