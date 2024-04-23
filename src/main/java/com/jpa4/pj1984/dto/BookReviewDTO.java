package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.BookReview;
import com.jpa4.pj1984.domain.BookReviewStatus;
import com.jpa4.pj1984.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewDTO { // 가져오는

    private Long bookReviewId;
    private Long userNo;
    private String userId;
    private String userName;
    private String bookReviewDetail;
    private BookReviewStatus bookReviewStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Long bookReviewPosition;
    private Integer step;
    private Long bookId;

    public BookReviewDTO(BookReview bookReview) {
        this.bookReviewId = bookReview.getBookReviewId();
        this.userNo = bookReview.getMember().getUserNo();
        this.userId = bookReview.getMember().getUserId();
        this.userName = bookReview.getMember().getUserName();
        this.bookReviewDetail = bookReview.getBookReviewDetail();
        this.createDate = bookReview.getCreateDate();
        this.lastModifiedDate = bookReview.getLastModifiedDate();
        this.bookReviewStatus = bookReview.getBookReviewStatus();
        this.bookReviewPosition = bookReview.getBookReviewPosition();
        this.step = bookReview.getStep();
        this.bookId = bookReview.getBook().getBookId();
    }

}
