package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Store extends TimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long store_id;
    @Column(nullable = false, unique = true, length = 100)
    private String store_title;
    @OneToOne
    @JoinColumn(name = "user_no")
    private Member member;
    @Column(nullable = false, unique = true, length = 20)
    private String store_crn;
    @Column(nullable = false, unique = true, length = 100)
    private String store_email;
    @Column(nullable = false, unique = true)
    private Long store_phone_num;
    @Column(nullable = false, length = 50)
    private String store_image_name;
    @Column(nullable = false, length = 100)
    private String store_image_id;
    @Column(nullable = false, length = 50)
    private String store_inside_image_name01;
    @Column(nullable = false, length = 100)
    private String store_inside_image_id01;
    @Column(nullable = false, length = 50)
    private String store_inside_image_name02;
    @Column(nullable = false, length = 100)
    private String store_inside_image_id02;
    @Column(nullable = false, length = 50)
    private String store_inside_image_name03;
    @Column(nullable = false, length = 100)
    private String store_inside_image_id03;
    @Column(nullable = false, length = 100)
    private String store_address;
    @Column
    private Long bank_id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus store_status;
    @Column(length = 100)
    private String store_operate_time;
    @Column(length = 400)
    private String store_tag;
    @Column(length = 30)
    private String bank_name;
    @Column(length = 30)
    private String store_account;


}


