package com.jpa4.pj1984.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/cms")
@RequiredArgsConstructor
public class BannerController {

    // 배너 리스트
    @GetMapping("/banner/list")
    public String bannerList() {
        log.info("******* BannerController list");


        return "backend/banner/list";
    }

}
