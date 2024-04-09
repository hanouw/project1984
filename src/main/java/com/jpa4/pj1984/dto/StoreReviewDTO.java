package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.domain.StoreReviewStatus;
import com.jpa4.pj1984.domain.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreReviewDTO { // 가져오는

    private Long storeReviewId;
    private Member member;
    private String storeReviewDetail;
    private StoreReviewStatus storeReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public StoreReviewDTO(StoreReview storeReview) {
        this.storeReviewId = storeReview.getStoreReviewId();
        this.member = storeReview.getMember();
        this.storeReviewDetail = storeReview.getStoreReviewDetail();
        this.createDate = storeReview.getCreateDate();
        this.lastModifiedDate = storeReview.getLastModifiedDate();
        this.storeReviewStatus = storeReview.getStoreReviewStatus();
    }

}
