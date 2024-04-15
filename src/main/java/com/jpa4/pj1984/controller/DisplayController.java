package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.DisplayDTO;
import com.jpa4.pj1984.service.DisplayService;
import com.jpa4.pj1984.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;


@Controller
@Slf4j
@RequestMapping("/cms")
@RequiredArgsConstructor
public class DisplayController {

    private final DisplayService displayService;
    private final FileUploadService fileUploadService;

    // 이미지 데이터 요청
//    @ResponseBody
//    @GetMapping("/images/{fileName}")
//    public Resource getImages(@PathVariable("fileName") String fileName) throws MalformedURLException {
//        return new UrlResource("file:" + fileUploadService.getPath(fileName));
//    }

    // 전시 상세
    @GetMapping("/display/detail")
    public String displayDetail(Model model) {
        DisplayDTO display = displayService.findOne();
        model.addAttribute("display", display);
        return "backend/display/detail";
    }

    // 전시 수정
    @GetMapping("/display/modify")
    public String displayModifyForm() {
        log.info("******* DisplayController modify");

        return "backend/display/modify";
    }
    // 전시 수정처리
    @PostMapping("/display/modify")
    public String displayModifyPro() {
        log.info("******* DisplayController modify");

        return "redirect:/cms/display/detail";
    }





}
