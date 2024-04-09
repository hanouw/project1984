package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.domain.BookStatus;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.service.BookCategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
    private Long bookId;
    private String isbn;
    private MultipartFile bookImg;
    private MultipartFile bookFile;
    private String bookTitle;
    private String bookWriter;

    private Store store;

    private String bookPub;
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
        book.setBookId(bookId);
        book.setIsbn(isbn);
        book.setBookImgOrg(bookImg.getOriginalFilename());
        book.setBookImgStored(bookImg.getName());
        book.setBookFileOrg(bookFile.getOriginalFilename());
        book.setBookFileStored(bookFile.getName());
        book.setBookTitle(bookTitle);
        book.setBookWriter(bookWriter);
        book.setStore(store);

        book.setBookPub(bookPub);
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

