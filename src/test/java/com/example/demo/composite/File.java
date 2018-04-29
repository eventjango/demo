package com.example.demo.composite;

public class File implements Entry {

    private String name;

    public File(String name){
        this.name = name;
    }

    @Override
    public void name() {
        System.out.println("name : " + name);
    }

    @Override
    public void add(Entry entry) {
        throw new FileTreatmentException("file cannot contain any entry", new UnsupportedOperationException());
    }

    @Override
    public int compareTo(Object o) {
        return o.toString().length();
    }
}
