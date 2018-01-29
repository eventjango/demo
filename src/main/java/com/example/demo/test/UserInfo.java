package com.example.demo.test;


import lombok.Builder;
import lombok.Data;

@Data
public class UserInfo {

    private String name;

    private String email;

    @Builder
    public UserInfo(String name, String email){
        this.name =  name;
        this.email = email;
    }
}
