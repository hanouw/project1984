package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Store extends TimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long storeId;
    @Column(nullable = false, unique = true, length = 100)
    private String storeLoginId;
    @Column(nullable = false, length = 100)
    private String storePassword;
    @Column(nullable = false, unique = true, length = 200)
    private String storeEmail;
    @Column(nullable = false, unique = true, length = 100)
    private String storeTitle;
    @Column(nullable = false, length = 20)
    private String storeOwner;
    @Column(nullable = false, unique = true, length = 20)
    private String storeCrn;
    @Column(length = 100)
    private String storeText;
    @Column(nullable = false, unique = true, length = 400)
    private String storePhoneNum;
    @Column(length = 100)
    private String storeAddress;
    @Column(length = 100)
    private String storeOneReview;
    @Column(length = 100)
    private String storeReview;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "storeReviewId")
    private List<StoreReview> storeUserReviewList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus storeStatus;

    @ManyToOne
    @JoinColumn(name = "membership_no")
    private Membership membership;

    @Column(length = 100)
    private String storeOperateTime;
    @Column(length = 400)
    private String storeTag;
    @Column(length = 30)
    private String storeAccount;
    @Column(length = 30)
    private String storeBankName;

    // 찢어야 함 String
    @Column(length = 50)
    private String storeImageOrigin;
    @Column
    private String storeImageStored;
    @Column(length = 50)
    private String storeImageOrigin01;
    @Column
    private String storeImageStored01;
    @Column(length = 50)
    private String storeImageOrigin02;
    @Column
    private String storeImageStored02;
    @Column(length = 50)
    private String storeImageOrigin03;
    @Column
    private String storeImageStored03;

    @PostLoad
    private void initDefaultMembership() {
        if (membership == null) {
            membership = new Membership(1L); // Create a Membership object with membershipNo=1
        }
    }


}


