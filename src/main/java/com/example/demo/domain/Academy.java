package com.example.demo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACADEMY_NO_SEQ")
    @SequenceGenerator(name = "ACADEMY_NO_SEQ", sequenceName = "ACADEMY_NO_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "academy_id")
    private List<Subject> subjects;

    {
        subjects = new ArrayList<>();
    }

    @Builder
    public Academy(String name, List<Subject> subjects){

        this.name = name;

        if(Objects.nonNull(subjects)) this.subjects = subjects;
    }

    public void addSubject(final Subject subject){
        this.subjects.add(subject);
        subject.updateAcademy(this);
    }

}
