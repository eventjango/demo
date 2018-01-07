package com.example.demo;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetTest {

    @Test
    public void add(){

        Set<? super String> set = new HashSet<>(Arrays.asList("kevin"));

        set.add("may");
        assertEquals(2, set.size());
    }

    @Test
    public void addAll(){

        Set<? super String> set = new HashSet<>(Arrays.asList("may"));

        set.addAll(Arrays.asList("kevin", "jack"));
        assertTrue(set.containsAll(Arrays.asList("may", "jack", "kevin")));
    }

    @Test
    public void addTreeSet(){

        TreeSet<? super String> set = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));
        set.addAll(Arrays.asList("jack", "may"));

        assertEquals(2, set.size());
        assertEquals(
                "may",
                set.pollFirst()
        );

        assertTrue(!set.contains("may"));
    }

    @Test
    public void remove(){

        HashSet<? super String> hashSet = new HashSet<>(Arrays.asList("kevin"));

        hashSet.addAll(Arrays.asList("may", "jack"));
        assertEquals(3, hashSet.size());

        hashSet.removeAll(Arrays.asList("jack", "kevin"));

        assertEquals(1, hashSet.size());
        assertTrue(hashSet.contains("may"));
    }

    @Test
    public void removeTreeSet(){

        TreeSet<? super String> treeSet = new TreeSet<>(Arrays.asList("may"));

        treeSet.add("kevin");
        treeSet.removeIf(s -> ((String) s).equals("may"));

        assertEquals(1, treeSet.size());
        assertTrue(treeSet.contains("kevin"));
    }

    @Test
    public void headAndTail(){

        TreeSet<? super String> treeSet = new TreeSet<>(Comparator.comparing(String::length, Comparator.naturalOrder()));

        treeSet.addAll(Arrays.asList("may", "kevin", "jack"));
        assertEquals(3, treeSet.size());

        assertEquals(2, treeSet.headSet("kevin").size());
        assertEquals(1, treeSet.headSet("jack").size());
        assertTrue(treeSet.headSet("may").isEmpty());

        assertEquals(3, treeSet.tailSet("may").size());
        assertEquals(2, treeSet.tailSet("jack").size());
        assertEquals(1, treeSet.tailSet("kevin").size());
    }

    @Test
    public void quadraticSet(){

        List<? super TreeSet<? extends String>> treeSetList = new ArrayList<>();

        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("may")));
        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("kevin")));

        assertEquals(2, treeSetList.size());
        assertEquals(1, ((TreeSet<String>) treeSetList.get(0)).size());
        assertEquals(1, ((TreeSet<String>) treeSetList.get(treeSetList.size() - 1)).size());

        Iterator<TreeSet<? extends String>> listIterator = (Iterator<TreeSet<? extends String>>) treeSetList.listIterator();

        while (listIterator.hasNext()){

            TreeSet<String> treeSet = (TreeSet<String>) listIterator.next();
            treeSet.forEach(System.out::println);
        }
    }

    @Test
    public void decending(){

        List<? super TreeSet<? super String>> treeSetList = new ArrayList<>();

        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("may", "jack")));
        treeSetList.add(treeSetList.size(), new TreeSet<>(Arrays.asList("kevin", "tommy")));

        treeSetList
                .stream()
                .forEach(
                        set -> {

                            ((TreeSet<?super String>) set).descendingSet().forEach(System.out::println);
                        }
                );

        treeSetList
                .stream()
                .forEach(
                        set -> {

                            Iterator<? super String> iterator = ((TreeSet<?super String>) set).descendingIterator();
                            iterator.forEachRemaining(System.out::println);
                        }
                );
    }



}

