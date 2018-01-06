package com.example.demo;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

        collection.add("may");
    }

    /**
     * Collection.addAll(Collection</? extends E> c) : boolean
     */
    @Test
    public void addAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("may"));

        assertTrue(collection.contains("may"));
        assertEquals(1, collection.size());

        collection.addAll(Arrays.asList("kevin", "jack"));

        assertTrue(collection.containsAll(Arrays.asList("kevin", "may", "jack")));
        assertEquals(3, collection.size());
    }


}
