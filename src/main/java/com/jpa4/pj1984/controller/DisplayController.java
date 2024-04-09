package com.jpa4.pj1984.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/cms")
@RequiredArgsConstructor
public class DisplayController {

    // 전시 상세
    @GetMapping("/display/detail")
    public String displayDetail() {
        log.info("******* DisplayController list");

        return "backend/display/detail";
    }

    // 전시 수정
    @GetMapping("/display/modify")
    public String displayModifyForm() {
        log.info("******* DisplayController modify");

        return "backend/display/modify";
    }
    // 전시 수정처리
    @PostMapping("/display/modify")
    public String displayModifyPro() {
        log.info("******* DisplayController modify");

        return "redirect:/cms/display/detail";
    }





}
