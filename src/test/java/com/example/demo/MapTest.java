package com.example.demo;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {

    @Test
    public void putIf(){

        Map<String, ? super Integer> map = new HashMap<>();

        map.putIfAbsent("code", 0);
        map.putIfAbsent("level", (Integer) map.getOrDefault("code", -1) + 1);

        map.computeIfPresent("level", (s, v) -> {assertEquals(1, v); return v; });
    }

    @Test
    public void merge(){

        Map<String, Integer> map = new HashMap<>();

        map.putAll(new HashMap<String, Integer>(){{
            this.putIfAbsent("code", 0);
            this.putIfAbsent("level", 1);
        }});

        assertEquals(2, map.size());
        map.merge("code", 0, Integer::sum);

        System.out.println(map.getOrDefault("code", -1));
    }

    @Test
    public void compute(){

        Map<String, Object> map = new HashMap<>();
        map.putAll(new HashMap<String, Object>(){{

            this.putIfAbsent("manager", "kevin");
            this.putIfAbsent("leader", "may");
        }});

        assertEquals(2, map.size());

        map.computeIfPresent("manager", (s, o) -> ((String)o).toUpperCase());
        map.computeIfPresent("leader", (s, o) -> ((String)o).toUpperCase());
    }

    @Test
    public void replaceAll(){

        Map<String, Object> map = new HashMap<>();

        map.putAll(
                new HashMap<String, Object>(){{
                    this.putIfAbsent("manager", "kevin");
                    this.putIfAbsent("leader", "may");
        }});


        map.replaceAll((s, o) -> {

            Object result = o;

            if("manager".equals(s)) result = ((String)o).toUpperCase();
            return result;

        });

        map.entrySet().forEach(System.out::println);
    }

    @Test
    public void treeMap(){

        TreeMap<String, Object> treeMap = new TreeMap<>(Comparator.comparing(String::length, Comparator.reverseOrder()));

        treeMap.putAll(
                new HashMap<String, Object>(){{
                    this.put("manager", "kevin");
                    this.put("leader", "may");
                    this.put("cap", "jack");
                }}
        );

        assertEquals("manager", treeMap.headMap("leader").firstKey());
        assertEquals(2, treeMap.headMap("cap").size());

        assertTrue(treeMap.tailMap("manager").containsKey("manager"));
        assertTrue(treeMap.tailMap("manager").containsKey("cap"));
    }
}
