package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.domain.StoreReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreReviewDTO { // 가져오는

    private Long storeReviewId;
    private Long userNo;
    private String userId;
    private String userName;
    private String storeReviewDetail;
    private StoreReviewStatus storeReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long storeReviewPosition;
    private Integer step;
    private Long storeId;

    public StoreReviewDTO(StoreReview storeReview) {
        this.storeReviewId = storeReview.getStoreReviewId();
        this.userNo = storeReview.getMember().getUserNo();
        this.userId = storeReview.getMember().getUserId();
        this.userName = storeReview.getMember().getUserName();
        this.storeReviewDetail = storeReview.getStoreReviewDetail();
        this.createDate = storeReview.getCreateDate();
        this.lastModifiedDate = storeReview.getLastModifiedDate();
        this.storeReviewStatus = storeReview.getStoreReviewStatus();
        this.storeReviewPosition = storeReview.getStoreReviewPosition();
        this.step = storeReview.getStep();
        this.storeId = storeReview.getStore().getStoreId();
    }

}
