package com.example.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.Index;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@ToString
@Table(name = "member")
@EqualsAndHashCode(of = "id")
public class Member {

    @Id
    @Index
    private String id;

    @Column
    private String password;

    @Column
    private String name;
}
