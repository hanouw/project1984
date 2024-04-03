package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.dto.BookCategoryDTO;
import com.jpa4.pj1984.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    //목록조회
//    public List<BookCategoryDTO> findAll() {
//        List<BookCategory> all = bookCategoryRepository.findAll();
//        List<BookCategoryDTO> list = all.stream()
//                .map(b -> new BookCategoryDTO(b))
//                .collect(Collectors.toList());
//        return list;
//    }

    //조회

    //저장
    public BookCategory save(BookCategoryDTO bookCategoryDTO){
        BookCategory bookCategorySaved = bookCategoryRepository.save(bookCategoryDTO.toEntity());
        return bookCategorySaved;
    }

    //수정


}
