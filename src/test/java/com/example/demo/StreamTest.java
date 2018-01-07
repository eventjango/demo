package com.example.demo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * 1. 생성
 * 2. 필터링
 * 3. 변환
 * 4. 매핑
 * 5. 수집
 * 6. 리덕션(리듀스, 리듀싱)
 * 7. 그루핑
 */
@Slf4j
public class StreamTest {

    /**
     * Stream.of(T... values) : Stream<T>
     * Arrays.stream(T[] array) : Stream<T>
     * Stream.empty() : Stream<T>
     */
    @Test
    public void create(){

        assertEquals(2L
                , Stream.of("kevin", "may").count()
        );

        Stream.of(Arrays.asList("may", "jack"))
                .forEach(array -> array.stream().forEach(System.out::println));

        assertEquals(2
                , Arrays.stream(Arrays.asList("kevin", "may").toArray(new String[]{})).count()
        );

        Stream<String> empty = Stream.empty();

        List<? super String> result = empty.collect(Collectors.toList());
        result.addAll(Arrays.asList("may", "tommy"));

        result
                .stream()
                .map(n -> ((String)n).toUpperCase())
                .forEach(System.out::println);

        IntStream
                .range(0, 10)
                .forEach(System.out::println);
    }

    @Test
    public void optional(){

        Optional<String> result =
        Arrays.stream(Arrays.asList("kevin", "may").toArray(new String[]{}))
                .map(String::toUpperCase)
                .findFirst();

        Optional<List<? extends String>> result2 =

        result
                .flatMap(string -> Optional.of(new ArrayList(Arrays.asList(string))))
                .map(list -> {list.clear(); return list; });


        if(result2.isPresent()){
            assertEquals(0, result2.orElseGet(() -> Arrays.asList()).size());
            /*assertTrue(result2.orElseThrow(() -> new NoResultException()).contains("KEVIN"));*/
        }

        Optional<String> empty =
        Optional
                .empty()
                .flatMap(o -> Optional.ofNullable("value"));

        System.out.println(empty);

    }

    @Test
    public void fromTo(){

        long size =
        Arrays.stream(Arrays.asList("may", "kevin").toArray(new String[0]), 0, 2)
                .count();

        assertEquals(2, size);
    }


    @Test
    public void generate(){

        Stream<String> stringStream = Stream.generate(() -> "may").limit(10);
        assertEquals(1, stringStream.distinct().count());
    }

    @Test
    public void iterate(){

        Stream<Integer> integerStream = Stream.iterate(1, n -> n+1).limit(10);
        integerStream.forEach(o -> log.debug(o.toString()));
    }

    @Test
    public void filter(){

        assertTrue(
                Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "may")))
                        .stream()
                        .filter(o -> ((String)o).startsWith("m"))
                        .anyMatch(o -> o.toString().equals("may"))
        );
    }

    @Test
    public void sort(){

        boolean result =

                Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("jack", "kevin")))
                        .stream()
                        .sorted(Comparator.comparing(s -> ((String)s).length(), Comparator.reverseOrder()))
                        .findFirst()
                        .get()
                        .equals("kevin");

        assertTrue(result);
    }

    @Test
    public void map(){

        boolean result =
        Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("may", "tommy")))
                .stream()
                .map(o -> ((String)o).toUpperCase())
                .allMatch(((Predicate)n -> n.toString().equals("MAY")).or(n -> n.toString().equals("TOMMY")));

        assertTrue(result);
    }

    @Test
    public void flatMap(){

        boolean result =

        Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("may", "kevin")))
                .stream()
                .flatMap(n -> Stream.of(n.toString().toUpperCase()))
                .allMatch(((Predicate)o -> o.equals("MAY")).or(o -> o.equals("KEVIN")));

        assertTrue(result);
    }

    @Test
    public void concat(){

        Stream
                .concat(
                        Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("may", "kevin")))
                        .stream()
                        .flatMap(string -> Stream.of(string)),

                        Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("jack", "tommy")))
                        .stream()
                        .flatMap(string -> Stream.of(String.valueOf(string)))
                        .map(s -> s.toUpperCase())
                )

                .forEach(System.out::println);
    }


    @Test
    public void maxAndMin(){

        assertEquals(0,
                IntStream
                .range(0, 5)
                .min()
                .getAsInt());

        assertEquals(4,
                IntStream
                .range(0, 5)
                .max()
                .getAsInt()
        );

        Supplier<Stream<String>> streamSupplier = () -> Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("jack", "may", "kevin")))
                .stream()
                .flatMap(n -> Stream.of(n.toString()));

        Optional<String> max =
                streamSupplier.get()
                .max(Comparator.comparing(String::length));

        assertEquals("kevin", max.get());

        Optional<String> min =
                streamSupplier.get()
                .min(Comparator.comparing(String::length));

        assertEquals("may", min.orElse("empty"));
    }

    @Test
    public void sum(){

        assertEquals(
                1+2+3+4,
                IntStream
                        .range(1, 5)
                        .sum()
        );
    }

    @Test
    public void findFirstAndFindAny(){

        Supplier<Stream<String>> streamSupplier = () -> Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("jack", "may", "kevin")))
                .stream()
                .flatMap(n -> Stream.of(n.toString()));


        Optional<String> first =

        streamSupplier
                .get()
                .filter(s -> s.substring(1, 2).equals("a"))
                .findFirst();

        System.out.println("first : " + first.orElse(""));


        Optional<String> any =

        streamSupplier
                .get()
                .filter(s -> s.substring(1, 2).equals("a"))
                .parallel()
                .findAny();

        System.out.println("any : " + any.orElse(""));
    }


    @Test
    public void anyMatch(){

        boolean result =

        Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("kevin", "jack")))
                .parallelStream()
                .flatMap(n -> Stream.of(n.toString()))
                .anyMatch(n -> n.contains("jack"));

        assertTrue(result);
    }

    @Test
    public void noneMatch(){

        boolean result =

        Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("may", "paul")))
                .parallelStream()
                .flatMap(n -> Stream.of(String.valueOf(n)))
                .noneMatch(n -> n.contains("kevin"));

        assertTrue(result);
    }


    @Test
    public void toArray(){

        String[] resultArray =

        Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("may", "kevin")))
                .parallelStream()
                .flatMap(string -> Stream.of(String.valueOf(string)))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(resultArray));
    }


    @Test
    public void joining(){

        assertEquals(

                "jack".concat("-").concat("may"),

                Stream
                        .empty()
                        .collect(Collectors.toCollection(() -> Arrays.asList("jack", "may")))
                        .parallelStream()
                        .map(o -> String.valueOf(o))
                        .collect(Collectors.joining("-"))
        );
    }

    /**
     * 집계 오브젝트로 리듀싱.
     */

    @Test
    public void summarizing(){

        IntSummaryStatistics intSummaryStatistics =

        Stream
                .empty()
                .collect(Collectors.toCollection(() -> Arrays.asList("may", "paul")))
                .parallelStream()
                .map(o -> String.valueOf(o))
                .collect(Collectors.summarizingInt(String::length));

        System.out.println("sum : " + intSummaryStatistics.getSum());
        System.out.println("max : " + intSummaryStatistics.getMax());
        System.out.println("min : " + intSummaryStatistics.getMin());
        System.out.println("average : " + intSummaryStatistics.getAverage());
    }

    @Test
    public void toMap(){

        @Getter
        @Setter
        @ToString
        class Person{

            int age;
            String name;

            Person(int age, String name){
                this.age = age;
                this.name = name;
            }

            Person(){}

            Optional<Person> empty(){
                Person person = new Person();
                return Optional.ofNullable(person);
            }
        }

        Stream<Person> persons =

        Arrays.asList(
                new Person(10, "kevin"), new Person(20, "may"), new Person(11, "kevin")
        ).parallelStream();


        Person result =
        persons
                .collect(
                        Collectors.toMap(
                                Person::getName,
                                Function.identity(),
                                (o, o2) -> o,
                                HashMap::new
                        )
                )
                .getOrDefault("kevin", new Person().empty().get());

        System.out.println(result);
    }

    @Test
    public void toMap2(){

        @Getter
        @Setter
        @ToString
        class Person implements Comparable<Person>{

            int age;
            String name;

            Person(int age, String name){
                this.age = age;
                this.name = name;
            }

            Person(){}

            Optional<Person> empty(){
                Person person = new Person();
                return Optional.ofNullable(person);
            }

            @Override
            public int compareTo(Person o) {
                return this.age - o.age;
            }
        }

        Stream<Person> persons =

        Arrays.asList(
                new Person(10, "kevin"), new Person(20, "may"), new Person(11, "kevin")).parallelStream();

        ConcurrentHashMap<String, ? super TreeSet<Person>> resultMap =

        persons
                .collect(
                        Collectors.toMap(

                                Person::getName,

                                person -> new TreeSet(Arrays.asList(person)),

                                (o1, o2) -> { o1.addAll(o2); return o1; },

                                ConcurrentHashMap::new
                        )
                );

        TreeSet<Person> find = (TreeSet<Person>) resultMap.get("kevin");

        find.
                stream()
                .sorted(Person::compareTo)
                .forEach(System.out::println);
    }

    @Test
    public void groupBy(){

        @Getter
        @Setter
        @ToString
        class Person implements Comparable<Person>{

            int age;
            String name;

            Person(int age, String name){
                this.age = age;
                this.name = name;
            }

            Person(){}

            Optional<Person> empty(){
                Person person = new Person();
                return Optional.ofNullable(person);
            }

            @Override
            public int compareTo(Person o) {
                return this.age - o.age;
            }
        }


        Stream<Person> persons =

                Arrays.asList(
                        new Person(10, "kevin"), new Person(20, "may"), new Person(11, "kevin")).parallelStream();

        persons
                .collect(
                        Collectors.groupingBy(

                                Person::getName,

                                HashMap::new,

                                Collectors.toCollection(
                                        () -> new TreeSet<Person>(Comparator.comparing(Person::getAge, Comparator.reverseOrder()))
                                )
                        )
                )

                .entrySet()

                .forEach(System.out::println);
    }

}
