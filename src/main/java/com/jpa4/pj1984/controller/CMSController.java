package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.service.CmsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cms")
public class CMSController {

    private final CmsService cmsService;

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

    // 서점 로그인 처리 요청
    @PostMapping("/login")
    public String loginPro(StoreLoginForm storeLoginForm, HttpSession httpSession) {
        log.info("******* CMSController loginPro");
        cmsService.login(storeLoginForm);
        return "redirect:/cms/home";
    }

    // 회원관리 - 회원 목록 조회
    @GetMapping("/userList")
    public String userList() {
        log.info("******* CMSController userList 호출");
        return "backend/member/memberList";
    }

    // 회원관리 - 회원 상세 정보 조회
    @GetMapping("/userDetail")
    public String userDetail() {
        return "backend/member/memberDetail";
    }

    // 주문관리 - 주문 목록 조회 판매자 ver (관리자 ver 필요)
    @GetMapping("/orderList")
    public String orderList(Model model
                            , PageRequestDTO pageRequestDTO
                            // @AuthenticationPrincipal CustomMember customMember
    ) {
        log.info("----CmsService pageRequestDTO : {}", pageRequestDTO);
        // customMember 에서 storeId 뽑아내기, 일단은 가라로 적음
        Long garaId = 1L;
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        List<PaymentResponseDTO> orderList = cmsService.findHistoryList(garaId, pageRequestDTO);
        model.addAttribute("orderList", orderList);
        Long count = cmsService.countHistoryList(garaId, pageRequestDTO);
        PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, count);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        log.info("**************CmsControlle orderList:{}", orderList);
        return "backend/order/list";
    }

    @GetMapping("/orderList/ajax")
    public ResponseEntity<PageResponseDTO> orderListAjax(PageRequestDTO pageRequestDTO) {
        log.info("----CmsService orderListAjax pageRequestDTO : {}", pageRequestDTO);
        // customMember 에서 storeId 뽑아내기, 일단은 가라로 적음
        Long garaId = 1L;
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        List<PaymentResponseDTO> orderList = cmsService.findHistoryList(garaId, pageRequestDTO);
        Long count = cmsService.countHistoryList(garaId, pageRequestDTO);
        PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, count, orderList);
        log.info("----CmsService orderListAjax pageResponseDTO : {}", pageResponseDTO);
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    // 주문관리 - 주문 상세 조회
    @GetMapping("/orderDetail")
    public String orderDetail() {
        log.info("******* CMSController orderDetail 호출");
        return "backend/order/detail";
    }

}

