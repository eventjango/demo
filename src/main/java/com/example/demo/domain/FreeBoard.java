package com.example.demo.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = @Index(unique = false, columnList = "boardNo"))
@ToString
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FREE_BOARD_NO_SEQ")
    @SequenceGenerator(name = "FREE_BOARD_NO_SEQ", sequenceName = "FREE_BOARD_NO_SEQ", allocationSize = 1)
    private Long boardNo;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL)
    private List<FreeBoardReply> replyList;
}
