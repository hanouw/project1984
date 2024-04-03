package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.dto.BookCategoryDTO;
import com.jpa4.pj1984.dto.BookCategoryForm;
import com.jpa4.pj1984.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    //목록조회
    public List<BookCategoryDTO> findAll() {
        List<BookCategory> all = bookCategoryRepository.findAll();
        List<BookCategoryDTO> list = all.stream()
                .map(b -> new BookCategoryDTO(b))
                .collect(Collectors.toList());
        return list;
    }

    //조회

    //저장
    public BookCategory save(BookCategoryForm bookCategoryForm){
        BookCategory bookCategorySaved = bookCategoryRepository.save(bookCategoryForm.toEntity());
        return bookCategorySaved;
    }

    //수정


}
