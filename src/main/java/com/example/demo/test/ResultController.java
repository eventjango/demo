package com.example.demo.test;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResultController {


    @GetMapping("/result")
    public String user(HttpServletRequest request, Model model){

        return request.getAttribute("user").toString();
    }
}
