package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.domain.StoreReviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookReviewForm { // 뿌려주는

    private Long bookReviewId;
    private Member member;
    private String bookReviewDetail;
    private StoreReviewStatus bookReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    // DTO -> Entity
    public StoreReview toEntity() {
        StoreReview storeReview = new StoreReview();
        storeReview.setStoreReviewId(bookReviewId);
        storeReview.setMember(member);
        storeReview.setStoreReviewDetail(bookReviewDetail);
        storeReview.setStoreReviewStatus(bookReviewStatus);
        return storeReview;
    }
}
