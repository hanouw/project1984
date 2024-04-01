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
    @Column(name = "member_id")
    private Long user_no;
    @Column(nullable = false, unique = true)
    private String user_id;
    @Column(nullable = false)
    private String user_password;
    @Column(nullable = false)
    private String user_name;
    @Column(nullable = false)
    private String user_email;
    @Column(nullable = false)
    private String user_phone_num;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role user_role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status user_status;
    @Column(updatable = false)
    private LocalDateTime regDate;


}