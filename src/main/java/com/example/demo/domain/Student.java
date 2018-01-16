package com.example.demo.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_ID_SEQ")
    @SequenceGenerator(name = "STUDENT_ID_SEQ", sequenceName = "STUDENT_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private double salary;

    public Student(String name, int age, double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public Student(){}
}
