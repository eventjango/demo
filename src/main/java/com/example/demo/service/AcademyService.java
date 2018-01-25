package com.example.demo.service;

import com.example.demo.domain.Academy;
import org.springframework.stereotype.Service;

@Service
public class AcademyService {

    public void createAcademy(){

        Academy academy = Academy.builder()
                .name("test01")
                .build();

        System.out.println(academy);
    }


    public static void main(String[] params){

        new AcademyService().createAcademy();
    }
}



