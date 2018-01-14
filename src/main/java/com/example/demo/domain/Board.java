package com.example.demo.domain;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table
public class Board {

    @Id
    @javax.jdo.annotations.Index
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
    @SequenceGenerator(name = "BOARD_SEQ", sequenceName = "BOARD_SEQ", allocationSize = 1)
    private Long no;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column(updatable = false)
    private String writer;

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    public Board
            (
            String title,
            String content,
            String writer)
    {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board(){}
}
