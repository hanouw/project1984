package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookReview extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookReviewId;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private Member member;
    @Column(nullable = false)
    private String bookReviewDetail;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookReviewStatus bookReviewStatus;

    private Long bookReviewPosition; // grouping = id
    private Integer step; // 댓글 순서

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
}
