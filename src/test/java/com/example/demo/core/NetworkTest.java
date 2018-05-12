package com.example.demo.core;


import lombok.Data;
import org.junit.Test;

import java.util.*;

class Network{

    @Data
    protected class Member{

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
        Network.Member kevin = network.create("kevin");

        kevin.join();
        kevin.setOthers(new ArrayDeque<>(
                Arrays.asList(
                        network.create("jack"),
                        network.create("may")
                )
        ));
        /*member.leave();*/

        System.out.println(network.members());
    }
}
