package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookReviewForm { // 뿌려주는

    private Long bookReviewId;
    private Member member;
    private String bookReviewDetail;
    private BookReviewStatus bookReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    // DTO -> Entity
    public BookReview toEntity() {
        BookReview bookReview = new BookReview();
        bookReview.setBookReviewId(bookReviewId);
        bookReview.setMember(member);
        bookReview.setBookReviewDetail(bookReviewDetail);
        bookReview.setBookReviewStatus(bookReviewStatus);
        return bookReview;
    }
}
