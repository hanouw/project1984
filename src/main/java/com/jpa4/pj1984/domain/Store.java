package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Store extends TimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long storeId;
    @Column(nullable = false, unique = true, length = 100)
    private String storeTitle;
    @Column(nullable = false, length = 20)
    private String storeOwner;
    @Column(nullable = false, unique = true, length = 20)
    private String storeCrn;
    @Column(unique = true, length = 100)
    private String storeText;
    @Column(nullable = false, unique = true, length = 400)
    private Long storePhoneNum;
    @Column(length = 50)
    private String storeImageName;
    @Column(length = 100)
    private String storeImageId;
    @Column(length = 50)
    private String storeInsideImageName01;
    @Column(length = 100)
    private String storeInsideImageId01;
    @Column(length = 50)
    private String storeInsideImageName02;
    @Column(length = 100)
    private String storeInsideImageId02;
    @Column(length = 50)
    private String storeInsideImageName03;
    @Column(length = 100)
    private String storeInsideImageId03;
    @Column(length = 100)
    private String storeAddress;
    @Column(length = 100)
    private String storeOneReview;
    @Column(length = 100)
    private String storeReview;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus storeStatus;
    @Column(length = 100)
    private String storeOperateTime;
    @Column(length = 400)
    private String storeTag;
    @Column(length = 30)
    private String storeBankName;
    @Column(length = 30)
    private String storeAccount;


}


