package com.jpa4.pj1984.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cms")
public class ReviewController {

    @GetMapping("/review/list")
    public String reviewListForm(){
        return "errorPage";
    }
}
