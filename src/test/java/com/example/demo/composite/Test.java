package com.example.demo.composite;

public class Test {

    @org.junit.Test
    public void composite(){

        Entry file = new File("file");
        Entry directory = new Directory("directory");

        file.name();

        try {

            file.add(file);
        }
        catch (FileTreatmentException e){

            e.printStackTrace();
        }

        finally {

            directory.add(file);
            directory.name();
        }
    }
}
