package com.example.demo;


import com.example.demo.domain.FreeBoard;
import com.example.demo.domain.FreeBoardReply;
import com.example.demo.repository.FreeBoardReplyRepository;
import com.example.demo.repository.FreeBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNotNull;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "local")
public class FreeBoardTest {


    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    FreeBoardReplyRepository freeBoardReplyRepository;


    @Test
    public void create(){

        assertNotNull(freeBoardRepository);
        assertNotNull(freeBoardReplyRepository);
    }


    @Test
    @Commit
    public void insertDummy(){

        IntStream
                .range(0, 3)
                .forEach(
                        n -> {

                            FreeBoard freeBoard = new FreeBoard();

                            freeBoard.setTitle("title" + n);
                            freeBoard.setContent("freeBoard" + n);

                            freeBoardRepository.save(freeBoard);
                        }
                );
    }


    @Test
    @Commit
    public void insertReplyDummy(){


        FreeBoard freeBoard = freeBoardRepository.findOne(1L);

        List<FreeBoardReply> replyList = freeBoard.getReplyList();

        FreeBoardReply freeBoardReply = new FreeBoardReply();
        freeBoardReply.setReply("reply in freeBoard0");
        freeBoardReply.setReplyer("reply0");

        freeBoardReply.setFreeBoard(freeBoard);

        replyList.add(freeBoardReply);
        freeBoard.setReplyList(replyList);

        freeBoardRepository.save(freeBoard);
    }


    @Test
    @Commit
    public void insertReplyOneWay(){

        FreeBoard freeBoard = freeBoardRepository.findOne(2L);

        FreeBoardReply freeBoardReply = new FreeBoardReply();
        freeBoardReply.setReply("reply in freeBoard1");
        freeBoardReply.setReply("reply1");
        freeBoardReply.setFreeBoard(freeBoard);

        freeBoardReplyRepository.save(freeBoardReply);
    }

    @Test
    @Commit
    public void updateReply(){

        FreeBoard freeBoard = freeBoardRepository.findOne(2L);

        FreeBoardReply freeBoardReply = freeBoard.getReplyList().get(0);

        freeBoardReply.setReply("reply in freeBoard1");
        freeBoardReply.setReplyer("reply1");

        freeBoardRepository.save(freeBoard);
    }

    @Test
    @Transactional(readOnly = true)
    public void selectBoardAndReply(){

        freeBoardRepository
                .findOne(1L)
                .getReplyList()
                .forEach(System.out::println);
    }
}
