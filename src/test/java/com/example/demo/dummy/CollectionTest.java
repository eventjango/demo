package com.example.demo.dummy;


import lombok.extern.java.Log;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Log
public class CollectionTest {


    /**
     * Collection.add(E element) : boolean
     */
    @Test(expected = UnsupportedOperationException.class)
    public void add(){

        Collection<? super String> collection = Collections.singletonList("kevin");

        assertNotNull(collection);
        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        collection.add("jack");
    }

    /**
     * Collection.addAll(Collection<\? extends E> c) : boolean
     */
    @Test
    public void addAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertTrue(collection.contains("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.addAll(Arrays.asList("jack", "may")));
        assertTrue(collection.containsAll(Arrays.asList("may", "kevin", "jack")));
        assertEquals(3, collection.size());
    }
}
