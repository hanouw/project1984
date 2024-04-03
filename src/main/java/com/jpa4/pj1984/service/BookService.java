package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Book;

import com.jpa4.pj1984.dto.BookDTO;
import com.jpa4.pj1984.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //저장
    public Book save(BookDTO bookDTO){
        Book bookSaved = bookRepository.save(bookDTO.toEntity());
        return bookSaved;
    }

}
