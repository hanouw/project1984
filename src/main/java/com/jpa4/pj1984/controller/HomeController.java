package com.jpa4.pj1984.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String homeController(){
        return "home";
    }

    @GetMapping("/order/book")
    public String bookOrder() { // 임시
        return "frontend/order/book/buy";
    }

    @GetMapping("/cms/home")
    public String cmsHome(){
        return "backend/home/home";
    }
}
