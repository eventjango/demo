package com.example.demo;

import static org.junit.Assert.*;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
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

    /**
     * Collection.remove(Object o) : boolean
     */
    @Test
    public void remove(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));

        assertEquals(true, collection.contains("kevin"));
        assertEquals(1, collection.size());

        assertTrue(collection.remove("kevin"));

        assertTrue(!collection.contains("kevin"));
        assertTrue(collection.isEmpty());
    }


    /**
     * Collection.removeAll(Collection</?> c) : boolean
     */
    @Test
    public void removeAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("may"));

        collection.addAll(Arrays.asList("kevin", "jack"));
        assertEquals(3, collection.size());

        assertTrue(collection.removeAll(new ArrayDeque<>(Arrays.asList("jack", "kevin"))));
        assertTrue(collection.contains("may"));

        collection.clear();

        assertTrue(Objects.nonNull(collection));
        assertTrue(collection.isEmpty());
    }


    /**
     * Collection.retainAll(Collection</?> c) : boolean
     */
    @Test
    @Synchronized
    public void retainAll(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("may", "kevin"));

        collection.add("jack");
        assertEquals(3, collection.size());

        collection.retainAll(Arrays.asList("may", "kevin"));
        assertTrue(!collection.contains("jack"));
    }

    /**
     * Collection.removeIf(Predicate</? super E> filter) : boolean
     */
    @Test
    public void removeIf(){

        Collection<? super String> collection = new ArrayList<>(Arrays.asList("kevin"));
        collection.addAll(Arrays.asList("may", "jack"));

        collection.removeIf(s -> ((String)s).startsWith("j"));

        assertTrue(!collection.contains("jack"));
        assertTrue(collection.containsAll(Arrays.asList("kevin", "may")));
        assertEquals(2, collection.size());
    }


    /**
     * Collection.iterator() : Iterator<E>
     */
    @Test
    public void iterator(){

        Collection<? extends String> collection = Arrays.asList("kevin", "may", "jack");
        collection = Collections.unmodifiableCollection(collection);

        Iterator<? extends String> iterator = collection.iterator();

        while (iterator.hasNext()){

            String element = iterator.next();
            log.warn(element.toUpperCase());
            log.debug(element);
        }
    }


    /**
     * Collection.toArray() : Object[], Collection.toArray(T[] t) : T[]
     */
    @Test
    public void toArray(){

        Collection<? extends String> collection = Arrays.asList("kevin", "may");

        Object[] resultByObject = collection.toArray();
        String[] resultByString = collection.toArray(new String[]{});

        log.warn(Arrays.toString(resultByObject));
        log.debug(Arrays.toString(resultByString));

        assertTrue(
                Arrays.stream(resultByObject)
                .findAny()
                .get() instanceof Object
        );

        assertTrue(
                Arrays.stream(resultByString)
                .findAny()
                .get() instanceof String
        );
    }

}
