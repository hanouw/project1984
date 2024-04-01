package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String userPhoneNum;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role userRole;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status userStatus;
    @Column(updatable = false)
    private LocalDateTime regDate;
    @OneToOne(mappedBy = "member")
    private Store store;
}