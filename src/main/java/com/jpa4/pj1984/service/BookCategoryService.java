package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.dto.BookCategoryDTO;
import com.jpa4.pj1984.dto.BookCategoryForm;
import com.jpa4.pj1984.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    //저장
    public Long save(BookCategoryForm bookCategoryForm){
        BookCategory bookCategorySaved = bookCategoryRepository.save(bookCategoryForm.toEntity());
        return bookCategorySaved.getBookCategoryId();
    }

    //목록조회
    public List<BookCategoryDTO> findAll() {
        List<BookCategory> all = bookCategoryRepository.findAll();
        System.out.println("all = " + all);//all까지는 불러옴
        List<BookCategoryDTO> list = all.stream()
                .map(b -> new BookCategoryDTO(b))
                .collect(Collectors.toList());
        System.out.println("list = " + list);
        return list;
    }

    //조회(1개)
    public BookCategoryDTO findOne(Long id) {
        BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(null);
        System.out.println("bookCategory = " + bookCategory);

        log.info("조회된 카테고리 ID : " + bookCategory.getBookCategoryId());
        log.info("조회된 카테고리 NAME : " + bookCategory.getBookCategoryName());
        return new BookCategoryDTO(bookCategory);
    }

    //수정
    public void updateOne(BookCategoryForm bookCategoryForm){
        BookCategory bookCategory = bookCategoryRepository.findById(bookCategoryForm.getBookCategoryId()).orElse(null);
        bookCategory.setBookCategoryName(bookCategoryForm.getBookCategoryName());
    }


}
