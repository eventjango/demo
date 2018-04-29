package com.example.demo.composite;

import java.util.TreeSet;
import java.util.Vector;

public class Directory implements Entry {

    public TreeSet<Entry> files;

    private String name;


    public Directory(String name){
        this.name = name;
        files = new TreeSet<>();
    }

    @Override
    public void name() {

        while (!files.isEmpty()){
            files.pollLast().name();
        }

        System.out.println("name : " + name);
    }

    @Override
    public void add(Entry entry) {
        files.add(entry);
    }

    @Override
    public int compareTo(Object o) {
        return o.toString().length();
    }
}
