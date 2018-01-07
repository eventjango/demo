package com.example.controller;

import com.example.respository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/index")
    public String index(){

        return "hello world";
    }

    @GetMapping("/board")
    public Long board(){

        return boardRepository.count();
    }

}
