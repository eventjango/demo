package com.example.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBJECT_NO_SEQ")
    @SequenceGenerator(name = "SUBJECT_NO_SEQ", sequenceName = "SUBJECT_NO_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_ACADEMY"))
    private Academy academy;


    public Subject(String name, Academy academy){
        this.name = name;
        this.academy = academy;
    }

    public void updateAcademy(Academy academy){
        this.academy = academy;
    }
}
