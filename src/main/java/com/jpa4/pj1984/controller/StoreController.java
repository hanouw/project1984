package com.jpa4.pj1984.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/store")
public class StoreController {

    @GetMapping("/add")
    public String addForm() {
        log.info("***** ");
        return "";
    }


}
