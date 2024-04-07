package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.domain.BookStatus;
import com.jpa4.pj1984.domain.Store;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDTO {
    private Long isbn;
    private String bookImg;
    private String bookFile;
    private String bookTitle;
    private String bookWriter;

    private Store store;

    private String bookPub;
    private String bookPubDate;
    private String bookPaperPrice;
    private String bookEbookPrice;

    private BookCategory bookCategory;

    private String bookIntro;
    private String bookIndex;
    private String bookReview;
    private String bookWriterProfile;
    private BookStatus bookStatus;
    private LocalDateTime createDate;

    //Entity -> DTO
    public BookDTO(Book book){
        this.isbn = book.getIsbn();
        this.bookImg = book.getBookImg();
        this.bookFile = book.getBookFile();
        this.bookTitle = book.getBookTitle();
        this.bookWriter = book.getBookWriter();

        this.store = book.getStore();

        this.bookPub = book.getBookPub();
        this.bookPubDate = book.getBookPubDate();
        this.bookPaperPrice = book.getBookPaperPrice();
        this.bookEbookPrice = book.getBookEbookPrice();

        this.bookCategory = book.getBookCategory();

        this.bookIntro = book.getBookIntro();
        this.bookIndex = book.getBookIndex();
        this.bookReview = book.getBookReview();
        this.bookWriterProfile = book.getBookWriterProfile();
        this.bookStatus = book.getBookStatus();
    }

}
