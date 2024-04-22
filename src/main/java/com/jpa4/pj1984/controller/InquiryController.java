package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.domain.StoreStatus;
import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.security.domain.CustomCms;
import com.jpa4.pj1984.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/cms/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    // 문의 목록 조회 요청
    @GetMapping("/list")
    public String inquiryList(@ModelAttribute("pageResponseDTO") PageResponseDTO pageResponseDTO, PageRequestDTO pageRequestDTO){
        pageRequestDTO.setPage(1);
        pageRequestDTO.setDateOrder("desc");
        return "backend/inquiry/list";
    }

    // ajax 문의 목록 조회 요청
    @GetMapping("/list/ajax")
    public ResponseEntity<InquiryPageResponseDTO> inquiryListAjax(PageRequestDTO pageRequestDTO, @AuthenticationPrincipal CustomCms customCms) {
        log.info("----InquiryController inquiryListAjax pageRequestDTO : {}", pageRequestDTO);
        StoreStatus storeStatus = customCms.getStore().getStoreStatus();
        if (storeStatus.getValue().equals("STATUS_ADMIN")) {
            List<InquiryDTO> list = inquiryService.findInquiryList(pageRequestDTO);
            Long count = inquiryService.countInquiryList(pageRequestDTO);
            InquiryPageResponseDTO pageResponseDTO = new InquiryPageResponseDTO(pageRequestDTO, count, list);
            return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
        } else {
            String storeTitle = customCms.getStore().getStoreTitle();
            List<InquiryDTO> inquiryList = inquiryService.findInquiryList(pageRequestDTO).stream()
                    .filter(l -> l.getStoreTitle().equals(storeTitle))
                    .collect(Collectors.toList());
            Long count = inquiryList.stream().count();
            InquiryPageResponseDTO pageResponseDTO = new InquiryPageResponseDTO(pageRequestDTO, count, inquiryList);
            log.info("----InquiryController inquiryListAjax pageResponseDTO : {}", pageResponseDTO);
            return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
        }
    }

    // 문의 상세
    @GetMapping("/{inquiryId}")
    public String detail(@PathVariable("inquiryId") Long inquiryId, Model model) {
        log.info("******* StoreReviewController detail");
        InquiryDTO inquiry = new InquiryDTO(inquiryService.getOneInquiry(inquiryId));

        log.info("******* InquiryController detail - inquiry : {}", inquiry);
        model.addAttribute("inquiry", inquiry);
        return "backend/inquiry/detail";
    }

    // 문의 답변 등록
    @GetMapping("/{inquiryId}/add")
    public String addForm(@PathVariable("inquiryId") Long inquiryId, Model model) {
        log.info("******* InquiryController addForm");
        InquiryDTO inquiry = new InquiryDTO(inquiryService.getOneInquiry(inquiryId));
        model.addAttribute("inquiry", inquiry);
        return "backend/inquiry/add";
    }
    // 문의 답변 등록처리
    @PostMapping("/{inquiryId}/add")
    public String addPro(@PathVariable("inquiryId") Long inquiryId, AnswerForm answerForm,
                         @AuthenticationPrincipal CustomCms customCms) {
        log.info("******* InquiryController addPro - answerForm : {}", answerForm);
        log.info("******* InquiryController addPro - customCms : {}", customCms);

        inquiryService.save(inquiryId, customCms.getStore().getStoreId() , answerForm); // 저장해~~
        return "redirect:/cms/inquiry/{inquiryId}";
    }

    // 문의 답변 수정
    @GetMapping("/{inquiryId}/modify")
    public String modifyForm(@PathVariable("inquiryId") Long inquiryId, Model model) {
        log.info("******* InquiryController modifyForm");
        InquiryDTO inquiry = new InquiryDTO(inquiryService.getOneInquiry(inquiryId));
        log.info("******* InquiryController modifyForm - inquiry.answer : {}", inquiry.getAnswerId());
        model.addAttribute("inquiry", inquiry);

        return "backend/inquiry/modify";
    }
    // 문의 답변 수정처리
    @PostMapping("/{inquiryId}/modify")
    public String modifyPro(@PathVariable("inquiryId") Long inquiryId, AnswerForm answerForm) {
        log.info("******* InquiryController modifyPro");
        log.info("******* InquiryController modifyPro - InquiryForm : {}", answerForm);

        inquiryService.updateOneAnswer(inquiryId, answerForm.toEntity()); // 수정해~~
        return "redirect:/cms/inquiry/{inquiryId}";
    }

}
