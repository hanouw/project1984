package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.BookReview;
import com.jpa4.pj1984.dto.BookReviewDTO;
import com.jpa4.pj1984.repository.BookReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookReviewService {

    private final BookReviewRepository bookReviewRepository;

    // 도서리뷰 목록 조회
    public List<BookReviewDTO> findAll() {
        List<BookReview> all = bookReviewRepository.findAll();
        System.out.println("all = " + all);//all까지는 불러옴
        List<BookReviewDTO> list = all.stream()
                .map(b -> new BookReviewDTO(b))
                .collect(Collectors.toList());
        System.out.println("list = " + list);
        return list;
    }

    // 댓글 상세 조회 (한개 조회)
    public BookReview getOneBookReview(Long bookReviewId) {

        BookReview bookReview = bookReviewRepository.findById(bookReviewId).orElse(null);

        return bookReview;
    }
}
