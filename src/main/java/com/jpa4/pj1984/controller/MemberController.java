package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.MemberForm;
import com.jpa4.pj1984.dto.MemberLoginDTO;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute MemberForm memberForm){ // 빈 객체를 전달하여 여기에 입력할 것이라는 것을 알려주는 역할
        log.info("******* MemberController signupForm");
        return "frontend/member/signup";
    }

    @PostMapping("/signup")
    public String signupPro(MemberForm memberForm){
        log.info("******* MemberController signupPro");
        Member member = memberService.save(memberForm);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute MemberLoginDTO memberLoginDTO){
        log.info("******* MemberController loginForm");
        return "frontend/member/login";
    }

    @PostMapping("/login")
    public String loginPro(MemberLoginDTO memberLoginDTO, HttpSession session, Model model){
        log.info("******* MemberController loginPro");
        MemberLoginDTO memberLoginDTOGiven = memberService.login(memberLoginDTO);
        if(memberLoginDTOGiven == null){
            return "redirect:/login";
        }
        session.setAttribute("memberLoginDTOGiven", memberLoginDTOGiven);
        return "frontend/home/main"; // 유저 홈 페이지
    }
}
