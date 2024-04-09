package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.BookReview;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.domain.StoreReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewDTO { // 가져오는

    private Long bookReviewId;
    private Member member;
    private String bookReviewDetail;
    private StoreReviewStatus bookReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public BookReviewDTO(BookReview bookReview) {
        this.bookReviewId = bookReview.getBookReviewId();
        this.member = bookReview.getMember();
        this.bookReviewDetail = bookReview.getBookReviewDetail();
        this.createDate = bookReview.getCreateDate();
        this.lastModifiedDate = bookReview.getLastModifiedDate();
    }

}
