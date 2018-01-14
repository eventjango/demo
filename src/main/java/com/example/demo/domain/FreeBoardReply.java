package com.example.demo.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(indexes = @Index(unique = false, columnList = "replyNo"))
@ToString(exclude = "freeBoard")
public class FreeBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FREE_BOARD_REPLY_NO_SEQ")
    @SequenceGenerator(name = "FREE_BOARD_REPLY_NO_SEQ", sequenceName = "FREE_BOARD_REPLY_NO_SEQ", allocationSize = 1)
    private Long replyNo;

    private String reply;

    private String replyer;

    @ManyToOne
    private FreeBoard freeBoard;
}
