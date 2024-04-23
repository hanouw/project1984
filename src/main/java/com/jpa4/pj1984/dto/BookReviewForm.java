package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookReviewForm { // 뿌려주는

    private Long bookReviewId;
    private String bookReviewDetail;
    private Long bookReviewPosition;
    private Integer step;
    private Long bookId;

    // DTO -> Entity
    public BookReview toEntity() {
        BookReview bookReview = new BookReview();
        bookReview.setBookReviewId(bookReviewId);
        bookReview.setBookReviewDetail(bookReviewDetail);
        bookReview.setBookReviewPosition(bookReviewPosition);
        bookReview.setStep(step);
        return bookReview;
    }
}
