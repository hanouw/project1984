package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.domain.BookCategoryStatus;
import lombok.Data;

import java.time.LocalDateTime;


@Data // 화면에 목록을 전달
public class BookCategoryDTO {

    private Long bookCategoryId;
    private String bookCategoryName;
    private BookCategoryStatus bookCategoryStatus;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime createDate;

    //Entity -> DTO
    public BookCategoryDTO(BookCategory bookCategory){
        this.bookCategoryId = getBookCategoryId();
        this.bookCategoryName = getBookCategoryName();
        this.bookCategoryStatus = getBookCategoryStatus();
        this.createDate = getCreateDate();
        this.lastModifiedDate = getLastModifiedDate();
    }
}
