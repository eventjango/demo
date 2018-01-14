package com.example.demo;


import com.example.demo.domain.Content;
import com.example.demo.domain.File;
import com.example.demo.repository.ContentRepository;
import lombok.extern.java.Log;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles(profiles = "local")
public class ContentTest {

    @Autowired
    ContentRepository contentRepository;


    @Test
    @Commit
    public void insertContent(){

        Content content = new Content();

        content.setName("content1");

        File file1 = new File();
        file1.setName("file1");

        File file2 = new File();
        file2.setName("file2");

        content.setFileList(Arrays.asList(file1, file2));

        contentRepository.save(content);
    }


    @Test
    @Commit
    public void updateFileName(){

        Content content = contentRepository.findByName("content1");

        assertNotNull(content);

        content.getFileList()
                .forEach(n -> n.setName("new name" + n.getId()));
    }

    @Test
    @Commit
    public void updateFileNameQuery(){

        int count = contentRepository.updateFileName(3L, "kevin3.file");
    }

    @Test
    public void selectContentList(){

        contentRepository
                .findAll()
                .forEach(System.out::println);
    }
}
