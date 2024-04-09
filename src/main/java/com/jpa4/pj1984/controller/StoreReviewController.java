package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.StoreReviewDTO;
import com.jpa4.pj1984.service.StoreReviewService;
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
@RequestMapping("/cms/storeReview")
public class StoreReviewController {

    private final StoreReviewService storeReviewService;

    // 서점댓글 목록 조회 요청
    @GetMapping("/list")
    public String List(Model model) {
        log.info("******* StoreController list");
        //DB에서 전체 게시글 데이터를 가져와서 List에 담아서 list.html로 전달
        List<StoreReviewDTO> storeReviewList = storeReviewService.findAll();
        model.addAttribute("storeReviewList", storeReviewList);
        System.out.println("storeReviewList = " + storeReviewList);

        return "backend/storeReview/list";
    }

    // 서점댓글 상세
    @GetMapping("/{storeReviewId}")
    public String detail(@PathVariable("storeReviewId") Long storeReviewId, Model model) {
        log.info("******* StoreReviewController detail");
        StoreReviewDTO storeReview = new StoreReviewDTO(storeReviewService.getOneStoreReview(storeReviewId));

        log.info("******* StoreReviewController detail - storeReview : {}", storeReview);
        model.addAttribute("storeReview", storeReview);
        return "backend/storeReview/detail";
    }
}
