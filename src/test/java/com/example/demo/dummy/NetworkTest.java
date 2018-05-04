package com.example.demo.dummy;


import lombok.Data;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Network{

    @Data
    public class Member{

        private String name;
        private Queue<Member> others;


        Member(String name){
            this.name = name;
            others = new ArrayDeque<>();
        }

        Member join(){
            Network.this.members.add(this); return this;
        }

        Member leave(){
            return Network.this.members.remove(members.indexOf(this));
        }
    }


    private List<Member> members = new ArrayList<>();

    public Member create(String name){
        return new Member(name);
    }


    public List<Member> members(){
        return members;
    }
}

public class NetworkTest {

    @Test
    public void create(){

        Network network = new Network();
        Network.Member member = network.create("kevin");
        member.join();
        member.leave();

        System.out.println(network.members());
    }
}
