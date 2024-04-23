package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.domain.StoreReviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreReviewForm { // 리뷰 저장할때 데이터 받아주는 폼객체

    private Long storeReviewId;
    private String storeReviewDetail;
    //    private StoreReviewStatus storeReviewStatus;
//    private LocalDateTime createDate;
//    private LocalDateTime lastModifiedDate;
    private Long storeReviewPosition;
    private Integer step;
    private Long storeId;


    // DTO -> Entity
    public StoreReview toEntity() {
        StoreReview storeReview = new StoreReview();
        storeReview.setStoreReviewId(storeReviewId);
//        storeReview.setMember(member);
        storeReview.setStoreReviewDetail(storeReviewDetail);
//        storeReview.setStoreReviewStatus(storeReviewStatus);
        storeReview.setStoreReviewPosition(storeReviewPosition);
        storeReview.setStep(step);
//        storeReview.setStore(store);
        return storeReview;
    }

}


