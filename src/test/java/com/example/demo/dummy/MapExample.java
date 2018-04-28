package com.example.demo.dummy;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class MapExample {


    @Test
    public void putIf(){

        Map<String, ? super Integer> map = new HashMap<>();

        map.putIfAbsent("code", 0);
        assertTrue(map.containsKey("code"));

        map.putIfAbsent("level", (Integer) map.getOrDefault("code", -1) + 1);
        assertTrue(map.containsKey("level"));

        // twofactor function compute : (k, v) -> v
        map.computeIfPresent("level",
                (string, integer) ->
                {
                    assertEquals(1, integer); return integer;
                });
    }


    @Test
    public void merge(){

        Map<String, Integer> map = new HashMap<>();

        map.putAll(

                new HashMap<String, Integer>()
                {
                    {
                        this.putIfAbsent("code", 0);
                        this.putIfAbsent("level", 1);
                    }
                }
        );

        assertEquals(2, map.size());

        map.merge("code", 0, (a, b) -> a + b);
        map.merge("level", 1, Integer::sum);

        map.computeIfPresent("code", (a, b) ->
        {
            System.out.println("key : " + a);
            System.out.println("value : " + b);
            return b;
        });

        map.compute("level",
                (a, b)
                ->
                {
                    System.out.println("key : " + a);
                    System.out.println("value : " + b);
                    return b;
                }
        );
    }


    @Test
    public void compute(){

        Map<String, Object> map = new HashMap<>();

        map.putAll(

                new HashMap<String, Object>()
                {
                    {
                        this.putIfAbsent("manager", "kevin");
                        this.putIfAbsent("leader", "jack");
                    }
                }
        );

        assertEquals(2, map.size());

        map.compute("manager", (a,b) -> b.toString().toUpperCase() );
        map.computeIfPresent("leader", (a, b) -> b.toString().length());

        map.entrySet().forEach(System.out::println);
    }


    @Test
    public void replaceAll(){

        Map<String, Object> map = new HashMap<>();

        map.putAll(
                new HashMap<String, Object>() {
                    {
                        this.putIfAbsent("manager", "kevin");
                        this.putIfAbsent("leader", "jack");
                    }
                }
        );

        map.replaceAll(

                (a, b) ->
                {
                    Object result = b;

                    if("manager".equals(a)) result = b.toString().toUpperCase();
                    return result;
                }
        );

        map.entrySet().forEach(System.out::println);
    }


    @Test
    public void treeMap(){

        Comparator<String> keyComparator = Comparator.comparing(String::length, Comparator.reverseOrder());
        TreeMap<String, Object> treeMap = new TreeMap<>(keyComparator.reversed());

        treeMap.putAll(
                new TreeMap<String, Object>()
                {
                    {
                        this.putIfAbsent("kevin", "KEVIN");
                        this.putIfAbsent("jack", "JACK");
                        this.putIfAbsent("may", "MAY");
                    }
                }
        );

        assertEquals(3, treeMap.size());
        assertEquals("may", treeMap.headMap("jack").firstKey());

        assertTrue(treeMap.tailMap("jack").lastKey().equals("kevin"));
        assertTrue(treeMap.tailMap("jack").containsKey("jack"));
        assertTrue(treeMap.headMap("kevin").containsKey("jack"));
    }


}
