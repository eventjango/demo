package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.jdo.annotations.Index;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString(exclude = "member")
@Table(name = "profile")
public class Profile {

    @Id
    @Index
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_NO_SEQ")
    @SequenceGenerator(name = "PROFILE_NO_SEQ", sequenceName = "PROFILE_NO_SEQ", allocationSize = 1)
    private Long no;

    @Column
    private String name;

    @Column
    private boolean current;

    @CreationTimestamp
    private Timestamp regDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;
}
