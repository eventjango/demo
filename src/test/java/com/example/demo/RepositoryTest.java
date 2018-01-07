package com.example.demo;

import com.example.respository.BoardRepository;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired(required = true)
    private BoardRepository boardRepository;


    @Test
    public void create(){

        assertNotNull(boardRepository);
    }

}
