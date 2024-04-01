package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.DTO.MemberDTO;
import com.jpa4.pj1984.service.CmsService;
import com.jpa4.pj1984.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cms")
public class CMSController {

    private final CmsService cmsService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute MemberDTO memberDTO){ // 빈 객체를 전달하여 여기에 입력할 것이라는 것을 알려주는 역할
        log.info("******* MemberController signupForm");
        return "backend/member/signup";
    }

    @PostMapping("/signup")
    public String signupPro(MemberDTO memberDTO){
        log.info("******* MemberController signupPro");
        cmsService.save(memberDTO);
        return "redirect:backend/home/home";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO){
        log.info("******* MemberController loginForm");
        return "backend/member/login";
    }

    @PostMapping("/login")
    public String loginPro(MemberDTO memberDTO, HttpSession httpSession){
        log.info("******* MemberController loginPro");
        cmsService.login(memberDTO);
        return "redirect:backend/home/home";
    }
}

