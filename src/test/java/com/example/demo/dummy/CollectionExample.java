package com.example.demo.dummy;

import lombok.Synchronized;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

@Log
public class CollectionExample {


    @Test(expected = UnsupportedOperationException.class)
    public void add(){

        Collection<? super String> collection = Collections.singleton("kevin");

        assertNotNull(collection);
        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.add("jack"));
    }


    @Test
    public void addAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertNotNull(collection);
        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.addAll(Arrays.asList("jack", "may")));

        assertEquals(3, collection.size());
        assertTrue(collection.containsAll(collection));
    }


    @Test
    public void remove(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertEquals(1, collection.size());
        assertTrue(collection.remove("kevin"));
        assertTrue(collection.isEmpty());
    }


    @Test
    public void removeAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.addAll(Arrays.asList("jack", "may")));
        assertEquals(3, collection.size());

        assertTrue(collection.removeAll(Arrays.asList("kevin", "jack")));
        assertEquals(1, collection.size());

        assertTrue(collection.contains("may"));
    }


    @Test
    @Synchronized
    public void retainAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin", "jack", "may"));

        assertEquals(3, collection.size());

        assertTrue(collection.retainAll(Arrays.asList("kevin", "jack")));  // kevin, jack 만 남겨라
        assertEquals(2, collection.size());
        assertTrue(!collection.contains("may"));
    }

    @Test
    public void removeIf(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));
        assertEquals(1, collection.size());

        collection.addAll(Arrays.asList("jack", "may"));
        assertEquals(3, collection.size());

        assertTrue(collection.removeIf(string -> string.toString().startsWith("j")));
        assertTrue(collection.containsAll(Arrays.asList("kevin", "may")));
        assertEquals(2, collection.size());
    }



    @Test
    public void iterator(){

        Collection<? super String> collection = new ArrayList<>();
        collection.addAll(Arrays.asList("kevin", "jack", "may"));

        assertEquals(3, collection.size());

        collection = Collections.unmodifiableCollection(collection);

        Iterator<? extends String> iterator = (Iterator<String>) collection.iterator();
        assertNotNull(iterator);

        while (iterator.hasNext()){

            String value = iterator.next();
            log.info("value : " + value);
        }
    }


    @Test
    public void toArray(){

        Collection<? super String> collection = Arrays.asList("kevin", "jack", "may");

        Object[] resultObjects = collection.toArray();
        String[] resultStrings = collection.toArray(new String[0]);

        assertTrue(Arrays.stream(resultObjects).findAny().get() instanceof Object);
        assertTrue(Arrays.stream(resultStrings).findAny().get() instanceof String);
    }


}
