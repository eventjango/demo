package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

@Slf4j
public class ListTest {

    /**
     * List.add(int index, E element) : boolean
     */
    @Test
    public void add(){

        List<? super String> list = new ArrayList<>(Arrays.asList("may"));

        list.add(list.size(), "kevin");
        assertEquals(2, list.size());
        assertTrue(list.containsAll(Arrays.asList("kevin", "may")));
    }

    /**
     * List.addAll(int index, Collection</? extends E> c) : boolean
     */
    @Test
    public void addAll(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("may", "kevin"));
        lists.add(lists.size(), Arrays.asList("jack"));

        assertEquals(2, lists.size());
    }

    /**
     * List.remove(int index) : Object
     */
    @Test
    public void remove(){

        List<? super String> list = new ArrayList<>(Arrays.asList("kevin"));

        list.add(list.size(), "may");
        assertEquals(2, list.size());

        assertEquals(list.remove(0), "kevin");
        assertTrue(list.contains("may"));
    }

    /**
     * List.remove(int index) : Object
     */
    @Test
    public void removeAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "may"));
        lists.add(lists.size(), Arrays.asList("jack"));

        assertEquals(2, lists.size());
        assertEquals(lists.remove(0), Arrays.asList("kevin", "may"));
        assertTrue(((List<? extends String>)lists.get(lists.size() - 1)).contains("jack"));
    }


    /**
     * List.indexOf(Object o) : int
     */
    @Test
    public void indexOf(){

        List<? extends String> list = Arrays.asList("kevin", "may");

        assertEquals(0, list.indexOf("kevin"));
        assertEquals(1, list.indexOf("may"));
    }


    /**
     * List.indexOf(Object o) : int
     */
    @Test
    public void indexOfAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "may"));
        lists.add(lists.size(), Arrays.asList("jack"));

        assertEquals(0, lists.indexOf(Arrays.asList("kevin", "may")));
        assertEquals(1, lists.indexOf(Arrays.asList("jack")));
    }


    /**
     * List.listIterator() : ListIterator<E>
     */
    @Test
    public void listIterator(){

        List<? extends String> list = Arrays.asList("may", "jack");

        ListIterator<? super String> listIterator = (ListIterator<String>) list.listIterator();


        while (listIterator.hasNext()){

            String element = (String) listIterator.next();

            if(element.startsWith("m")){

                listIterator.previous();
                listIterator.set(element.toUpperCase());
                element = (String) listIterator.next();
            }

            log.warn(element);
        }

        listIterator.forEachRemaining(n -> log.info(n.toString()));

    }

    /**
     * List.listIterator() : ListIterator<E>
     */
    @Test
    public void listIteratorAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may"));

        assertEquals(2, lists.size());


        lists.forEach(

                list -> {

                    ListIterator<? super String> listIterator
                            = (ListIterator<? super String>) ((List<? extends String>) list).listIterator();

                    while(listIterator.hasNext()){

                        String element = (String) listIterator.next();

                        if(element.startsWith("j")){

                            listIterator.previous();
                            listIterator.set(element.toUpperCase());
                            element = (String) listIterator.next();
                        }

                        log.debug(element);
                    }
                }
        );
    }

    /**
     * List.sort(Comparator</? super E> c) : void
     */
    @Test
    public void sort(){

        List<? extends String> list = Arrays.asList("jack", "may", "kevin");

        assertEquals(3, list.size());

        list.sort(Comparator.comparing(String::length, Comparator.naturalOrder()));

        assertEquals(
                "may",
                list
                .stream()
                .findFirst()
                .get()
        );


        list.sort(Comparator.comparing(String::length, Comparator.reverseOrder()));

        assertEquals(
                "kevin",
                list
                .stream()
                .findFirst()
                .get()
        );
    }


    /**
     * List.sort(Comparator</? super E> c) : void
     */
    @Test
    public void sortAt(){

        List<? super List<? extends String>> lists = new ArrayList<>();

        lists.add(lists.size(), Arrays.asList("kevin", "jack"));
        lists.add(lists.size(), Arrays.asList("may", "peter"));

        lists
                .forEach(
                        list -> {

                            ((List<? extends String>) list)
                                    .sort(
                                            Comparator.comparing(String::length, Comparator.naturalOrder())
                                    );
                        }
                );

        assertEquals(
                "jack",

                ((List<? extends String>)lists.get(0))
                .stream()
                .findFirst()
                .get()
        );


        assertEquals(
                "may",

                ((List<? extends String>) lists.get(1))
                .stream()
                .findFirst()
                .get()
        );


        ListIterator<? super List<? extends String>> listIterator = lists.listIterator();

        while (listIterator.hasNext()){

            List<? extends String> list = (List<? extends String>) listIterator.next();

            if(lists.indexOf(list) == 0)

                list
                        .sort(
                                Comparator.comparing(String::length, Comparator.naturalOrder())
                        );
            else if(lists.indexOf(list) == 1)

                list
                        .sort(
                                Comparator.comparing(String::length, Comparator.reverseOrder())
                        );

        }


        assertEquals(
                "jack",

                ((List<? extends String>) lists.get(0))
                .stream()
                .findFirst()
                .get()
        );

        assertEquals(
                "peter",

                ((List<? extends String>) lists.get(1))
                .stream()
                .findFirst()
                .get()
        );

    }


    /**
     * List.replaceAll(UnaryOperator<E> operator) : void
     */
    @Test
    public void replaceAll(){

        List<? super String> list = Arrays.asList("kevin", "may");

        list
                .replaceAll(s -> ((String) s).toUpperCase());

        assertEquals(
                "kevin".toUpperCase(),

                list
                .stream()
                .findFirst()
                .get()
        );

        assertTrue(list.containsAll(Arrays.asList("MAY", "KEVIN")));
    }


}
