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
    //base 테스트
    @GetMapping("/base")
    public String base(){

        return "backend/layouts/base";
    }

    //상품리스트
    @GetMapping("/book")
    public String bookList(){
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
    @GetMapping("/bookCategory")
    public String bookCategoryList(){
        log.info("--CMS--Book--Category--List--Request--");
        return "backend/bookcategory/list";
    }
    //상품카테고리추가폼
    @GetMapping("/bookCategory/add")
    public String bookCategoryAddForm(){
        log.info("--CMS--Book--Category--AddForm--Request--");
        return "backend/bookcategory/add";
    }
    //상품카테고리추가

}
