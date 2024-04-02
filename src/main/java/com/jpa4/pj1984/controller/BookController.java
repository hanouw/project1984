package com.jpa4.pj1984.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/cms")
public class BookController {
    //상품리스트
    @GetMapping("/book")
    public String booklist(){
        log.info("--CMS--Book--List--Request--");
        return "backend/book/list";
    }
    //상품추가폼
    @GetMapping("/book/add")
    public String bookAddForm(){
        log.info("--CMS--Book--AddForm--Request--");
        return "backend/book/add";
    }
    //상품추가
    @PostMapping("/book/add")
    public String bookAdd(){
        log.info("--CMS--Book-Add--Request--");
        return "redirect:/book";
    }

    //상품카테고리리스트

    //상품카테고리추가폼

    //상품카테고리추가

}
