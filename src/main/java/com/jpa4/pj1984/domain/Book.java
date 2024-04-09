package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Book extends TimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String bookImgOrg;
    private String bookImgStored;

    @Column(nullable = false)
    private String bookFileOrg; // 원본 파일명
    private String bookFileStored;// 저장된 파일명

    @Column(nullable = false)
    private String bookTitle;
    @Column(nullable = false)
    private String bookWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String bookPub;
    @Column
    private String bookPaperPrice;
    @Column(nullable = false)
    private String bookEbookPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_category_id")
    private BookCategory bookCategory;

    @Column
    private String bookIntro;
    @Column
    private String bookIndex;
    @Column
    private String bookReview;
    @Column
    private String bookWriterProfile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus;

}
