package com.jpa4.pj1984.controller;



import com.jpa4.pj1984.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    // 게시글 목록 요청
    @GetMapping("/list")
    public String list() {
        log.info("******* StoreController list");
        return "store/list";
    }

    @GetMapping("/add")
    public String addForm() {
        log.info("******* StoreController addForm");
        return "store/add";
    }

    @PostMapping("/add")
    public String addPro() {
        log.info("******* StoreController addPro");
        return "redirect:/store/list";
    }


}
/*
    @GetMapping("/login")
    public String loginForm(){
        log.info("******* MemberController loginForm");
        return "member/login";
    }

    @PostMapping("/login")
    public String loginPro(){
        log.info("******* MemberController loginPro");
        return "redirect:home";
    }
}
*/
