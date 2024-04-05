package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long isbn;
    private String bookImg;
    private String bookFile;
    private String bookTitle;
    private String bookWriter;
    private Long storeId;
    private String bookPub;
    private String bookPubDate;
    private String bookPaperPrice;
    private String bookEbookPrice;
    private Long categoryId;
    private String bookIntro;
    private String bookIndex;
    private String bookReview;
    private String bookWriterProfile;
    private BookStatus bookStatus;
    private LocalDateTime createDate;

    //DTO -> Entity
    public BookDTO(Book book){
        this.isbn = book.getIsbn();
        this.bookImg = book.getBookImg();
        this.bookFile = book.getBookFile();
        this.bookTitle = book.getBookTitle();
        this.bookWriter = book.getBookWriter();
        this.storeId = book.getStoreId();
        this.bookPub = book.getBookPub();
        this.bookPubDate = book.getBookPubDate();
        this.bookPaperPrice = book.getBookPaperPrice();
        this.bookEbookPrice = book.getBookEbookPrice();
        this.categoryId = book.getCategoryId();
        this.bookIntro = book.getBookIntro();
        this.bookIndex = book.getBookIndex();
        this.bookReview = book.getBookReview();
        this.bookWriterProfile = book.getBookWriterProfile();
        this.bookStatus = book.getBookStatus();
    }

}
