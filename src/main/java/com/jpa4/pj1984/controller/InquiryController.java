package com.jpa4.pj1984.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/cms")
@RequiredArgsConstructor
public class InquiryController {
    //문의리스트
    @GetMapping("/inquiry/list")
    public String inquiryList(Model model){
        log.info("--CMS--inquiry--List--Request--");
        return "backend/inquiry/list";
    }
}
