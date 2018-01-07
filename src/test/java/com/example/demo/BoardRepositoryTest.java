package com.example.demo;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


}
