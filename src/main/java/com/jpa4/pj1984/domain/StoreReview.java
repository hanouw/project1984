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
    private Long storeReviewId;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private Member member;
    @Column(nullable = false)
    private String storeReviewDetail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreReviewStatus storeReviewStatus;

    private Long storeReviewPosition; // grouping = id
    private Integer step; // 댓글 순서

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

}
