package com.jpa4.pj1984.controller;



import com.jpa4.pj1984.DTO.StoreForm;
import com.jpa4.pj1984.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    // 서점 목록 요청
    @GetMapping("/list")
    public String list() {
        log.info("******* StoreController list");
        return "backend/store/list";
    }

    // 서점 상세
    @GetMapping("/detail")
    public String addForm(@ModelAttribute StoreForm storeForm) {
        log.info("******* StoreController addForm");
        return "backend/store/detail";
    }

    // 서점 등록
    @PostMapping("/add")
    public String addPro(StoreForm storeForm) {
        log.info("******* StoreController addPro");

        // storeService.save(storeForm, "java01");
        return "redirect:/store/list";
    }


}

