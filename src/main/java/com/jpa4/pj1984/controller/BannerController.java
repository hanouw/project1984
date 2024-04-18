package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.BannerDTO;
import com.jpa4.pj1984.dto.BannerForm;
import com.jpa4.pj1984.service.BannerService;
import com.jpa4.pj1984.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/cms")
@RequiredArgsConstructor
public class BannerController {
    //--공통사용-----------------------------------------------------//
    private final BannerService bannerService;
    private final FileUploadService fileUploadService;

    //--배너관리-----------------------------------------------------//

    // 배너 추가폼
    @GetMapping("/banner/add")
    public String bannerAddForm(@ModelAttribute BannerForm bannerForm, Model model){
        log.info("--CMS--Banner--AddForm--Request--");
        return "backend/banner/add";
    }

    // 배너 추가
    @PostMapping("/banner/add")
    public String bannerAddPro(BannerForm bannerForm) throws Exception{
        log.info("--CMS--Banner--Add--Request-- bannerForm : {}", bannerForm);
        bannerService.save(bannerForm);
        return "redirect:/cms/banner/list";
    }

    // 배너 리스트
    @GetMapping("/banner/list")
    public String bannerList(Model model) {
        log.info("--CMS--Banner--List--Request--");
        List<BannerDTO> bannerDTOList = bannerService.findAll();
        model.addAttribute("bannerList", bannerDTOList);
        return "backend/banner/list";
    }

    //배너 상세
    @GetMapping("/banner/{id}")
    public String bannerDetail(@PathVariable("id") Long id, Model model){
        BannerDTO banner = bannerService.findOne(id);
        model.addAttribute("banner", banner);
        return "backend/banner/detail";
    }

    //배너수정
    @GetMapping("/banner/{id}/modify")
    public String bannerModifyForm(@PathVariable("id")Long id, Model model){
        BannerDTO banner = bannerService.findOne(id);
        model.addAttribute("banner", banner);
        return "backend/banner/modify";
    }
    @PostMapping("/banner/{id}/modify")
    public String bannerModifyPro(@PathVariable("id") Long id, BannerForm bannerForm) throws Exception{
        bannerService.updateOne(bannerForm);
        System.out.println("실행 확인 id = " + id + ", bannerForm = " + bannerForm);
        return "redirect:/cms/banner/{id}";
    }
    // 이미지 데이터 요청
    @ResponseBody
    @GetMapping("/bannerimages/{fileName}")
    public Resource getImages(@PathVariable("fileName") String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileUploadService.getPath(fileName));
    }
}
