package com.example.demo.controller;


import lombok.Data;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Enumeration;

@RestController
public class ApiTestController {

    @Data
    private static class RestMessage{

        private String message;

        public RestMessage(String message){
            this.message = message;
        }
    }

    @GetMapping("/")
    public RestMessage hello(HttpServletRequest request){

        Enumeration<String> headerNames =  request.getHeaderNames();

        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }

        System.out.println("authorization : " + request.getHeader("authorization"));

        return new RestMessage("Hello World");
    }


    @GetMapping("/api/test")
    public RestMessage test(){

        return new RestMessage("Hello ApiTest");
    }


    @GetMapping(value = "/api/hello", produces = "application/json")
    public RestMessage helloUser(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new RestMessage(String.format("Hello '%s'!", username));
    }

    @GetMapping("/api/admin")
    public RestMessage helloAdmin(Principal principal){

        return new RestMessage(String.format("Welcome '%s'", principal.getName()));
    }
}
