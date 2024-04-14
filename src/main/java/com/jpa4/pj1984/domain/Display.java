package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Display {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long displayId;
    @Column
    private String aboutImg01Org;
    @Column
    private String aboutImg01Stored;
    @Column
    private String aboutText01;
    @Column
    private String aboutText02;
    @Column
    private String aboutText03;
    @Column
    private String aboutText04;

    @Column
    private String aboutImg02Org;
    @Column
    private String aboutImg02Stored;
    @Column
    private String aboutText05;
    @Column
    private String aboutText06;

    @Column
    private String mainStore01;
    @Column
    private String mainStore02;
    @Column
    private String mainStore03;
    @Column
    private String mainStore04;
    @Column
    private String mainStore05;

    @Column
    private String mainBook01;
    @Column
    private String mainBook02;
    @Column
    private String mainBook03;
    @Column
    private String mainBook04;
    @Column
    private String mainBook05;
}
