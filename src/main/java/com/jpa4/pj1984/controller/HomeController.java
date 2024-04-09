package com.jpa4.pj1984.controller;



import com.jpa4.pj1984.security.domain.CustomCms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/orderDetail")
    public String bookOrder() { // 임시
        return "bookDetail";
    }

    @GetMapping("/")
    public String redirHome(){
        return "redirect:/cms/home";
    }

    @GetMapping("/cms/home")
    public String cmsHome(){
        return "/backend/home/dashboard";
    }

    @GetMapping("/error")
    public String errorPage(){
        return "/errorPage";
    }
}
