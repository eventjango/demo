package com.example.demo.entity;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void create(){

        assertNotNull(boardRepository);
    }

    @Test
    public void count(){

        assertEquals(
                0,
                boardRepository.count()
        );
    }

    @Test
    public void add(){

        Board board = new Board();

        board.setTitle("test title");
        board.setContent("fuck!!");
        board.setWriter("joe");

        boardRepository.save(board);
    }

    @Test
    public void update(){

        Board board = boardRepository.findOne(1L);

        assertNotNull(board);

        board.setContent("Fucking!!!! boot!!");
        boardRepository.save(board);
    }


    @Test
    @Transactional
    public void saveAndDelete(){

        boardRepository.deleteAll();
        assertEquals(0, boardRepository.count());

        List<Board> boardList = Arrays.asList(
                new Board("test01", "hello0", "kevin"),
                new Board("test02", "hello1", "jack")
        );

        boardRepository.save(boardList);
        assertEquals(2, boardRepository.count());

        boardRepository.delete(boardList);
        assertEquals(0, boardRepository.count());
    }


    @Test
    @Transactional
    public void find(){

        boardRepository.deleteAll();

        List<Board> boardList = Arrays.asList(
                new Board("test01", "hello0", "kevin"),
                new Board("test02", "hello1", "jack")
        );

        boardRepository.save(boardList);

        assertEquals(2, boardRepository.count());

        boardRepository
                .findAll(
                        (Iterable<Long>)
                                boardList
                                .stream()
                                .map(n -> n.getNo())
                                .collect(Collectors.toList()))
        .stream()
        .forEach(System.out::println);
    }

    @Test
    @Transactional
    public void insertAndUpdate(){

        boardRepository.deleteAll();

        List<Board> boardList = Arrays.asList(
                new Board("test01", "hello0", "kevin"),
                new Board("test02", "hello1", "jack")
        );

        boardRepository.save(boardList);

        assertEquals(2, boardRepository.count());

        boardList
        .replaceAll(n -> { n.setContent("fucking!!!"); return n; } );

        boardRepository.save(boardList);

        boardRepository
                .findAll()
                .forEach(System.out::println);


        boardRepository
                .findAll(
                        (Iterable<Long>)
                                boardList
                                        .subList(0, 1)
                                        .stream()
                                        .map(n -> n.getNo())
                                        .collect(Collectors.toList()))
                .stream()
                .forEach(System.out::println);
    }


    @Test
    @Transactional
    public void findBy(){

        boardRepository.save(new Board("test01", "hello world", "kevin"));

        assertEquals(1, boardRepository.count());

        assertEquals("test01", boardRepository.findByWriter("kevin").getTitle());
    }

    @Test
    @Transactional
    public void inTest(){

        List<Board> boardList = Arrays.asList(
                new Board("test01", "hello0", "kevin"),
                new Board("test02", "hello1", "jack")
        );

        boardRepository.save(boardList);

        List<Long> longList =
                boardRepository
                        .findAll()
                        .stream()
                        .map(n -> n.getNo())
                        .collect(Collectors.toList());


        List<Board> resultList =
        boardRepository
        .findByNoIn(longList);

        resultList
                .forEach(System.out::println);
    }

    @Test
    @Transactional
    public void pageTest(){

        List<Board> boardList = Arrays.asList(
                new Board("test01", "hello0", "kevin"),
                new Board("test02", "hello1", "jack")
        );

        boardRepository.save(boardList);

        int number = boardRepository.findAllByNoNotIn(Arrays.asList(0L), new PageRequest(0, 10)).getNumber();
        int size = boardRepository.findAllByNoNotIn(Arrays.asList(0L), new PageRequest(0, 10)).getSize();
        int totalPages = boardRepository.findAllByNoNotIn(Arrays.asList(0L), new PageRequest(0, 10)).getTotalPages();
        int elements = boardRepository.findAllByNoNotIn(Arrays.asList(0L), new PageRequest(0, 10)).getNumberOfElements();
        long totalElements = boardRepository.findAllByNoNotIn(Arrays.asList(0L), new PageRequest(0, 10)).getTotalElements();

        System.out.println(number);
        System.out.println(size);
        System.out.println(totalPages);
        System.out.println(elements);
        System.out.println(totalElements);
    }


    @Test
    @Transactional
    public void QueryTest(){

        boardRepository
                .save(
                        Arrays.asList(
                                new Board("test01", "hello01", "kevin"),
                                new Board("test02", "hello02", "jack")
                        )
                )

                .forEach(System.out::println);

        boardRepository.selectBoard(36L).forEach(System.out::println);

    }
}
