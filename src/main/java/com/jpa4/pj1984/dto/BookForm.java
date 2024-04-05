package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.domain.BookStatus;
import com.jpa4.pj1984.service.BookCategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
    private Long isbn;
    private String bookImg;
    private String bookFile;
    private String bookTitle;
    private String bookWriter;
//    private Long storeId;
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

    //DTO -> Entity
    public Book toEntity(){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setBookImg(bookImg);
        book.setBookFile(bookFile);
        book.setBookTitle(bookTitle);
        book.setBookWriter(bookWriter);

//        book.setStore(storeId);

        book.setBookPub(bookPub);
        book.setBookPubDate(bookPubDate);
        book.setBookPaperPrice(bookPaperPrice);
        book.setBookEbookPrice(bookEbookPrice);

        book.setBookCategory(bookCategory);

        book.setBookIntro(bookIntro);
        book.setBookIndex(bookIndex);
        book.setBookReview(bookReview);
        book.setBookWriterProfile(bookWriterProfile);
        book.setBookStatus(bookStatus);
        return book;
    }

}

