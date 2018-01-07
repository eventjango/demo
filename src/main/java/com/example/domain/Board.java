package com.example.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table
public class Board {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String writer;

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;
}
