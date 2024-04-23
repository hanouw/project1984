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
    @Column(nullable = false, unique = true)
    private String storeLoginId;
    @Column(nullable = false)
    private String storePassword;
    @Column(nullable = false, unique = true)
    private String storeEmail;
    @Column(nullable = false, unique = true)
    private String storeTitle;
    @Column(nullable = false)
    private String storeOwner;
    @Column(nullable = false, unique = true)
    private String storeCrn;
    private String storeText;
    @Column(nullable = false, unique = true)
    private String storePhoneNum;
    private String storeAddress;
    private String storeOneReview;
    private String storeReview;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "storeReviewId")
    private List<StoreReview> storeUserReviewList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus storeStatus;

    @ManyToOne
    @JoinColumn(name = "membership_no")
    private Membership membership;

    private String storeOperateTime;
    private String storeTag;
    private String storeAccount;
    private String storeBankName;

    // 찢어야 함 String
    private String storeImageOrigin;
    @Column
    private String storeImageStored;
    private String storeImageOrigin01;
    private String storeImageStored01;
    private String storeImageOrigin02;
    private String storeImageStored02;
    private String storeImageOrigin03;
    private String storeImageStored03;

    @PostLoad
    private void initDefaultMembership() {
        if (membership == null) {
            membership = new Membership(1L); // Create a Membership object with membershipNo=1
        }
    }


}


