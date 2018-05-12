package com.example.demo.core;

import static org.junit.Assert.*;

import lombok.extern.java.Log;
import org.junit.Test;

import java.util.*;


@Log
public class SetExample {

    @Test
    public void add(){

        Set<? super String> set = new HashSet<>(Arrays.asList("kevin"));

        set.add("jack");
        set.add("may");

        assertEquals(3, set.size());
    }

    @Test
    public void addAll(){

        Set<? super String> set = new LinkedHashSet<>();

        set.addAll(Arrays.asList("kevin", "jack", "may"));
        assertEquals(3, set.size());

        log.info("set : " + set.toString());
    }


    @Test
    public void addTreeSet(){

        TreeSet<? super String> treeSet = new TreeSet<>(Arrays.asList("kevin", "jack", "may"));
        assertEquals(3, treeSet.size());

        Comparator comparator = Comparator.comparing(string_super -> string_super.toString().length(), Comparator.naturalOrder());

        List<? super String> list = new ArrayList<>(treeSet);
        Collections.sort(list, comparator.reversed());

        list.forEach(System.out::println);


        treeSet = new TreeSet<>(comparator);
        treeSet.addAll(Arrays.asList("kevin", "jack", "may"));

        assertEquals(3, treeSet.size());
        log.info("treeSet : " + treeSet.toString());
    }


    @Test
    public void remove(){

        HashSet<? super String> hashSet = new HashSet<>(Arrays.asList("kevin"));
        hashSet.addAll(Arrays.asList("jack", "may"));
        assertEquals(3, hashSet.size());

        assertTrue(hashSet.remove("may"));
        assertEquals(2, hashSet.size());

        assertTrue(hashSet.removeAll(Arrays.asList("jack")));
        assertEquals(1, hashSet.size());
    }


    @Test
    public void removeTreeSet(){

        Comparator comparator = Comparator.comparing(String::length, Comparator.naturalOrder());
        TreeSet<? super String> treeSet = new TreeSet<>(comparator);

        assertTrue(treeSet.addAll(Arrays.asList("kevin", "jack", "may")));
        assertTrue(treeSet.removeIf(string -> string.toString().equals("jack")));

        log.info("treeSet : " + treeSet.toString());
    }


    @Test
    public void firstAndLast(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));
        treeSet.addAll(Arrays.asList("kevin", "jack", "may"));

        assertEquals(3, treeSet.size());
        assertEquals("may", treeSet.first());
        assertEquals("kevin", treeSet.last());
    }

    @Test // 초과
    public void headSet(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));

        treeSet.addAll(Arrays.asList("kevin", "jack", "may"));
        assertEquals(3, treeSet.size());

        assertTrue(treeSet.headSet("may").isEmpty());
        assertEquals(1, treeSet.headSet("jack").size());
        assertTrue(treeSet.headSet("kevin").contains("may") || treeSet.headSet("kevin").contains("jack"));
        assertTrue(treeSet.headSet("kevin").containsAll(Arrays.asList("jack", "may")));
    }


    @Test // 이하
    public void tailSet(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.reverseOrder()));

        treeSet.addAll(Arrays.asList("kevin", "jack", "may"));
        assertEquals(3, treeSet.size());

        assertEquals(treeSet.size(), treeSet.tailSet("kevin").size());
        assertEquals(1, treeSet.tailSet("may").size());
    }


    @Test
    public void setList(){

        List<? super TreeSet<? extends String>> treeSetList = new ArrayList<>();

        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("kevin", "jack")));
        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("may", "me")));

        assertEquals(2, treeSetList.size());


        ListIterator<TreeSet<? extends String>> listIterator = (ListIterator<TreeSet<? extends String>>) treeSetList.listIterator();

        while (listIterator.hasNext()){

            TreeSet<? extends String> treeSet = listIterator.next();
            treeSet.forEach(System.out::println);
        }
    }


    @Test
    public void descending(){

        List<? super TreeSet<? extends String>> treeSetList = new ArrayList<>();

        treeSetList.add(new TreeSet<>(Arrays.asList("kevin", "jack")));
        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("may", "me")));

        treeSetList.stream()
                .forEach(
                        set ->
                        {
                            ((TreeSet<? extends String>) set).descendingSet().forEach(System.out::println);
                        }
                );


        treeSetList.stream()
                .forEach(
                        set ->
                        {
                            Iterator<? super String> iterator = (Iterator<String>) ((TreeSet<? extends String>) set).descendingIterator();
                            iterator.forEachRemaining(System.out::println);
                        }
                );
    }
}
