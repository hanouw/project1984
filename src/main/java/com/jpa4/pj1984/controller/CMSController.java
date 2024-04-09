package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.service.CmsService;
import com.jpa4.pj1984.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cms")
public class CMSController {

    private final CmsService cmsService;
    private final MemberService memberService;

    // 서점 등록 폼 요청
    @GetMapping("/signup")
    public String signupForm(@ModelAttribute StoreForm storeForm) { // 빈 객체를 전달하여 여기에 입력할 것이라는 것을 알려주는 역할
        log.info("******* CMSController signupForm");
        return "backend/member/signup";
    }

    // 서점 등록 처리 요청
    @PostMapping("/signup")
    public String signupPro(StoreForm storeForm) {
        log.info("******* CMSController signupPro");
        cmsService.save(storeForm);
        return "redirect:/cms/login";
    }

    // 서점 로그인 폼 요청
    @GetMapping("/login")
    public String loginForm(@ModelAttribute StoreLoginForm storeLoginForm) {
        log.info("******* CMSController loginForm");
        return "backend/member/login";
    }

//    @PostMapping("/login")
//    public String loginPro(StoreLoginForm storeLoginForm, HttpSession httpSession){
//        log.info("******* CMSController loginPro");
//        cmsService.login(storeLoginForm);
//        return "redirect:/cms/home";
//    }

    //----------------------------------------------------------------------------------- 회원(이용자)
    // 회원관리 - 회원 목록 조회
    @GetMapping("/userList")
    public String userList(@ModelAttribute MemberDTO memberDTO, Model model) {
        log.info("******* CMSController userList 호출");
        List<MemberForm> allMember = memberService.findAllMember();
        model.addAttribute("allMember", allMember);
        return "backend/member/memberList";
    }

    // 회원관리 - 회원 상세 정보 조회
    @GetMapping("/userDetail/{userNo}")
    public String userDetail(@PathVariable Long userNo, Model model) {
        log.info("******* CMSController /userDetail/userNo = {}", userNo);
        MemberForm memberForm = memberService.findMemberById(userNo);
        model.addAttribute("memberForm", memberForm);
        return "backend/member/memberDetail";
    }

    // 회원 수정 - 페이지 출력
    @GetMapping("/userDetail/{userNo}/modify")
    public String userModify(@PathVariable Long userNo, Model model){
        log.info("******* CMSController /userDetail/userNo/modify = {}", userNo);
        MemberForm memberForm = memberService.findMemberById(userNo);
        model.addAttribute("memberForm", memberForm);
        return "backend/member/memberModify";
    }

    // 회원 수정 - 수정처리
    @PostMapping("/userDetail/{userNo}/modify")
    public String userModifyPro(MemberDTO memberDTO){
        log.info("*******  CMS Controller / POST / userModifyPro : modify");
        memberService.modifyMember(memberDTO);
        return "redirect:/cms/userDetail/{userNo}";
    }

    // 주문관리 - 주문 목록 조회 판매자 ver (관리자 ver 필요)
    @GetMapping("/orderList")
    public String orderList(Model model, PageRequestDTO pageRequestDTO
                            // @AuthenticationPrincipal CustomMember customMember
    ) {
        log.info("******* CMSController orderList 호출");
        // customMember 에서 storeId 뽑아내기, 일단은 가라로 적음
        Long garaId = 1111L;
        List<PaymentResponseDTO> orderList = cmsService.findHistoryList(garaId, pageRequestDTO);
        model.addAttribute("orderList", orderList);
        return "backend/order/list";
    }

    // 주문관리 - 주문 상세 조회
    @GetMapping("/orderDetail")
    public String orderDetail() {
        log.info("******* CMSController orderDetail 호출");
        return "backend/order/detail";
    }

    // ajax : 관리자 회원가입 중복 확인
    @PostMapping("/ajaxUsernameAvail")
    public ResponseEntity<String> ajaxUsernameAvail(String storeLoginId) {
        log.info("Controller /ajaxUsernameAvail - storeId : {}", storeLoginId);
        // username 사용 가능한지 DB 가서 체크
        String result = "이미 사용 중 입니다.";
        StoreDTO findMember = cmsService.findStoreById(storeLoginId);
        if(findMember==null){ // null -> DB에 없다 -> 사용 가능
            result = "사용 가능합니다.";
        }
        // 헤더정보 포함해서 응답 한글깨짐 방지
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Content-Type", "text/plain;charset=UTF-8");

        return new ResponseEntity<String>(result, responseHeader, HttpStatus.OK);
    }
}

