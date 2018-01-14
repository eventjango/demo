package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.Index;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table
public class File {

    @Id
    @Index
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ID_SEQ")
    @SequenceGenerator(name = "FILE_ID_SEQ", sequenceName = "FILE_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private String name;

}
