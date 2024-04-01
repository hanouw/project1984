package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.DTO.MemberDTO;
import com.jpa4.pj1984.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(){
        log.info("******* MemberController signupForm");
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signupPro(MemberDTO memberDTO){
        log.info("******* MemberController signupPro");
        memberService.save(memberDTO);
        return "redirect:home";
    }

    @GetMapping("/cms/login")
    public String loginForm(){
        log.info("******* MemberController loginForm");
        return "backend/member/cmslogin";
    }

    @PostMapping("/cms/login")
    public String loginPro(){
        log.info("******* MemberController loginPro");
        return "redirect:home";
    }
}
