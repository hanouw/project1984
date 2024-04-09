package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.BookReviewDTO;
import com.jpa4.pj1984.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cms/bookReview")
public class BookReviewController {

    private final BookReviewService bookReviewService;

    // 도서댓글 목록 조회 요청
    @GetMapping("/list")
    public String List(Model model) {
        log.info("******* BookReviewController list");
        //DB에서 전체 게시글 데이터를 가져와서 List에 담아서 list.html로 전달
        List<BookReviewDTO> bookReviewList = bookReviewService.findAll();
        model.addAttribute("bookReviewList", bookReviewList);
        System.out.println("bookReviewList = " + bookReviewList);

        return "backend/bookReview/list";
    }

    // 서점댓글 상세
    @GetMapping("/{bookReviewId}")
    public String detail(@PathVariable("bookReviewId") Long bookReviewId, Model model) {
        log.info("******* BookReviewController detail");
        BookReviewDTO bookReview = new BookReviewDTO(bookReviewService.getOneBookReview(bookReviewId));

        log.info("******* BookReviewController detail - bookReview : {}", bookReview);
        model.addAttribute("storeReview", bookReview);
        return "backend/bookReview/detail";
    }
}
