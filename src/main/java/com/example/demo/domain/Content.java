package com.example.demo.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.jdo.annotations.Index;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table
public class Content {

    @Id
    @Index
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTENT_ID_SEQ")
    @SequenceGenerator(name = "CONTENT_ID_SEQ", sequenceName = "CONTENT_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String writer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "content_no")
    private List<File> fileList;
}
