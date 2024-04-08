package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StoreReview extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long storeReviewId;
    @Column(nullable = false, unique = true, length = 50)
    private String userId;
    @Column(nullable = false, unique = true, length = 100)
    private String storeReview;
    @Column(nullable = false, length = 200)
    private Long storeReviewPosition;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus storeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

}
