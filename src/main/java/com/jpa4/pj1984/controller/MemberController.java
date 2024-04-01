package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.DTO.MemberDTO;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute MemberDTO memberDTO){ // 빈 객체를 전달하여 여기에 입력할 것이라는 것을 알려주는 역할
        log.info("******* MemberController signupForm");
        return "frontend/member/signup";
    }

    @PostMapping("/signup")
    public String signupPro(MemberDTO memberDTO){
        log.info("******* MemberController signupPro");
        Member member = memberService.save(memberDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute MemberDTO memberDTO){
        log.info("******* MemberController loginForm");
        return "frontend/member/login";
    }

    @PostMapping("/login")
    public String loginPro(MemberDTO memberDTO, HttpSession httpSession){
        log.info("******* MemberController loginPro");
        memberService.login(memberDTO);
        return "redirect:/";
    }
}
