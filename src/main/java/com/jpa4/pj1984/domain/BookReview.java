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
    @Column(nullable = false, length = 100)
    private String bookReviewDetail;
    @Column(nullable = false, length = 200)
    private Long bookReviewPosition;
    @Column(nullable = false)
    private Integer step;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookReviewStatus bookReviewStatus;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
}
