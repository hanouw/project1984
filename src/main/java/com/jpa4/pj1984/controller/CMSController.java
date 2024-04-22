package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.domain.StoreStatus;
import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.security.domain.CustomCms;
import com.jpa4.pj1984.service.CmsService;
import com.jpa4.pj1984.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

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
        List<MemberDTO> allMember = memberService.findAllMember();
        model.addAttribute("allMember", allMember);
        return "backend/member/memberList";
    }

    // 회원관리 - 회원 상세 정보 조회
    @GetMapping("/userDetail/{userNo}")
    public String userDetail(@PathVariable Long userNo, Model model) {
        log.info("******* CMSController /userDetail/userNo = {}", userNo);
        MemberDTO memberDTO = memberService.findMemberById(userNo);
        model.addAttribute("memberForm", memberDTO);
        return "backend/member/memberDetail";
    }

    // 회원 수정 - 페이지 출력
    @GetMapping("/userDetail/{userNo}/modify")
    public String userModify(@PathVariable Long userNo, Model model){
        log.info("******* CMSController /userDetail/userNo/modify = {}", userNo);
        MemberDTO memberDTO = memberService.findMemberById(userNo);
        model.addAttribute("memberForm", memberDTO);
        return "backend/member/memberModify";
    }

    // 회원 수정 - 수정처리
    @PostMapping("/userDetail/{userNo}/modify")
    public String userModifyPro(MemberDTO memberDTO){
        log.info("*******  CMS Controller / POST / userModifyPro : modify");
        memberService.modifyMember(memberDTO);
        return "redirect:/cms/userDetail/{userNo}";
    }

    // 구독관리 - 구독권 수정 상세페이지 조회
    @GetMapping("/membership/modify")
    public String membershipModify(Model model) {
        MembershipDTO membershipPrice = cmsService.findMembershipPrice();
        model.addAttribute("membershipPrice", membershipPrice);
        return "backend/member/membershipModify";
    }

    // 구독관리 - 구독권 수정 처리 요청
    @PostMapping("/membership/modify")
    public String membershipModifyPro(MembershipDTO membershipDTO, RedirectAttributes rttr) {
        cmsService.modifyMembershipPrice(membershipDTO);
        rttr.addFlashAttribute("success", "success");
        return "redirect:/cms/membership/modify";
    }

    // 구독관리 - 구독내역 상세페이지 조회
    @GetMapping("/order/membership/{orderMembershipNo}")
    public String membershipDetail(@PathVariable("orderMembershipNo") Long orderMembershipNo, Model model) {
        PaymentMemDTO memDTO = cmsService.findOneMemHistory(orderMembershipNo);
        model.addAttribute("order", memDTO);
        return "backend/member/membershipDetail";
    }

    // 구독 관리 - 목록 조회
    @GetMapping("/order/membershipList")
    public String userMembershipList(@AuthenticationPrincipal CustomCms customCms, Model model, PageRequestDTO pageRequestDTO) {
        pageRequestDTO.setPage(1);
        pageRequestDTO.setDateOrder("desc");
        StoreStatus storeStatus = customCms.getStore().getStoreStatus();
        if (storeStatus.getValue().equals("STATUS_ADMIN")) {
            MemPageResponseDTO pageResponseDTO = new MemPageResponseDTO(pageRequestDTO, cmsService.countMembershipList(pageRequestDTO), cmsService.findMembershipList(pageRequestDTO));
            model.addAttribute("pageResponseDTO", pageResponseDTO);
        } else {
            MemPageResponseDTO pageResponseDTO = new MemPageResponseDTO(pageRequestDTO, cmsService.countMembershipList(pageRequestDTO), cmsService.findMembershipList(pageRequestDTO));
            model.addAttribute("pageResponseDTO", pageResponseDTO);
        }
        return "backend/member/membershipList";
    }

    // ajax 구독 관리 - 목록 조회
    @GetMapping("/order/membershipList/ajax")
    public ResponseEntity<MemPageResponseDTO> userMembershipListAjax(@AuthenticationPrincipal CustomCms customCms,PageRequestDTO pageRequestDTO) {
        log.info("----CmsController userMembershipListAjax pageRequestDTO : {}", pageRequestDTO);
        StoreStatus storeStatus = customCms.getStore().getStoreStatus();
        if (storeStatus.getValue().equals("STATUS_ADMIN")) {
            List<PaymentMemDTO> membershipList = cmsService.findMembershipList(pageRequestDTO);
            Long count = cmsService.countMembershipList(pageRequestDTO);
            MemPageResponseDTO pageResponseDTO = new MemPageResponseDTO(pageRequestDTO, count, membershipList);
            return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
        } else {
            Long storeId = customCms.getStore().getStoreId();
            List<PaymentMemDTO> list = cmsService.findMembershipList(pageRequestDTO).stream()
                    .filter(l -> l.getStoreId() == storeId)
                    .collect(Collectors.toList());
            long count= list.stream().count();
            MemPageResponseDTO pageResponseDTO = new MemPageResponseDTO(pageRequestDTO, count, list);
            log.info("----CmsService orderListAjax memPageResponseDTO : {}", pageResponseDTO);
            return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
        }
    }

    // 주문관리 - 주문 목록 조회
    @GetMapping("/order/bookList")
    public String orderList(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal CustomCms customCms) {
        pageRequestDTO.setPage(1);
        pageRequestDTO.setDateOrder("desc");
        StoreStatus storeStatus = customCms.getStore().getStoreStatus();
        if (storeStatus.getValue().equals("STATUS_ADMIN")) {
            BookPageResponseDTO pageResponseDTO = new BookPageResponseDTO(pageRequestDTO, cmsService.countHistoryList(pageRequestDTO), cmsService.findHistoryList(pageRequestDTO));
            model.addAttribute("pageResponseDTO", pageResponseDTO);
        } else {
            BookPageResponseDTO pageResponseDTO = new BookPageResponseDTO(pageRequestDTO, cmsService.countHistoryList(pageRequestDTO), cmsService.findHistoryList(pageRequestDTO));
            model.addAttribute("pageResponseDTO", pageResponseDTO);
        }
        return "backend/order/bookList";
    }

    // ajax 주문관리 - 주문 목록 조회
    @GetMapping("/order/bookList/ajax")
    public ResponseEntity<BookPageResponseDTO> orderListAjax(PageRequestDTO pageRequestDTO, @AuthenticationPrincipal CustomCms customCms) {
        log.info("----CmsController orderListAjax pageRequestDTO : {}", pageRequestDTO);
        StoreStatus storeStatus = customCms.getStore().getStoreStatus();
        if (storeStatus.getValue().equals("STATUS_ADMIN")) {
            List<PaymentBookHistoryDTO> list = cmsService.findHistoryList(pageRequestDTO);
            Long count = cmsService.countHistoryList(pageRequestDTO);
            BookPageResponseDTO pageResponseDTO = new BookPageResponseDTO(pageRequestDTO, count, list);
            return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
        } else {
            String storeTitle = customCms.getStore().getStoreTitle();
            List<PaymentBookHistoryDTO> orderList = cmsService.findHistoryList(pageRequestDTO).stream()
                    .filter(l -> l.getStoreTitle() == storeTitle)
                    .collect(Collectors.toList());
            Long count = orderList.stream().count();
            BookPageResponseDTO bookPageResponseDTO = new BookPageResponseDTO(pageRequestDTO, count, orderList);
            log.info("----CmsService orderListAjax pageResponseDTO : {}", bookPageResponseDTO);
            return new ResponseEntity<>(bookPageResponseDTO, HttpStatus.OK);
        }
    }

    // 주문관리 - 주문 내역 상세페이지 조회
    @GetMapping("/order/book/{orderBookHistoryId}")
    public String orderBook(@PathVariable("orderBookHistoryId") Long orderBookHistoryId, Model model) {
        PaymentBookHistoryDTO bookHistoryDTO = cmsService.findOneBookHistory(orderBookHistoryId);
        model.addAttribute("order", bookHistoryDTO);
        return "backend/order/bookDetail";
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

//    @PostMapping("/ajaxEmailAvail")
//    public ResponseEntity<Boolean> ajaxEmailAvail(String storeLoginId) {
//        log.info("Controller /ajaxEmailAvail - storeId : {}", storeLoginId);
//        StoreDTO findMember = cmsService.findStoreById(storeLoginId);
//        if(findMember==null){ // null -> DB에 없다 -> 사용 가능
//            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<Boolean>(false, HttpStatus.OK);
//    }
//
//    @PostMapping("/ajaxTitleAvail")
//    public ResponseEntity<String> ajaxTitleAvail(String storeLoginId) {
//        log.info("Controller /ajaxTitleAvail - storeId : {}", storeLoginId);
//        // username 사용 가능한지 DB 가서 체크
//        String result = "이미 사용 중 입니다.";
//        StoreDTO findMember = cmsService.findStoreById(storeLoginId);
//        if(findMember==null){ // null -> DB에 없다 -> 사용 가능
//            result = "사용 가능합니다.";
//        }
//        // 헤더정보 포함해서 응답 한글깨짐 방지
//        HttpHeaders responseHeader = new HttpHeaders();
//        responseHeader.add("Content-Type", "text/plain;charset=UTF-8");
//
//        return new ResponseEntity<String>(result, responseHeader, HttpStatus.OK);
//    }
//
//    @PostMapping("/ajaxUsernameAvail")
//    public ResponseEntity<String> ajaxPhoneNumAvail(String storeLoginId) {
//        String result = null;
//        // 헤더정보 포함해서 응답 한글깨짐 방지
//        HttpHeaders responseHeader = new HttpHeaders();
//        responseHeader.add("Content-Type", "text/plain;charset=UTF-8");
//
//        return new ResponseEntity<String>(result, responseHeader, HttpStatus.OK);
//    }
}

