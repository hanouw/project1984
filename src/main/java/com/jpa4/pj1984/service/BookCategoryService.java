package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.dto.BookCategoryDTO;
import com.jpa4.pj1984.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    public BookCategory save(BookCategoryDTO bookCategoryDTO){
        BookCategory bookCategorySaved = bookCategoryRepository.save(bookCategoryDTO.toEntity());
        return bookCategorySaved;
    }

}
