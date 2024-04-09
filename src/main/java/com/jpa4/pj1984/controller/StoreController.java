package com.jpa4.pj1984.controller;



import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.domain.StoreStatus;
import com.jpa4.pj1984.dto.StoreDTO;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.service.FileUploadService;
import com.jpa4.pj1984.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cms/store")
public class StoreController {

    @Value("${file.dir}")
    private String fileDir;

    private final StoreService storeService;
    private final FileUploadService fileUploadService;


    @ModelAttribute("bankList")
    public List<String> banks() {
        log.info("************** bank 데이터 전송!");
        List<String> bankList = new ArrayList<>();
        bankList.add("국민은행");
        bankList.add("기업은행");
        bankList.add("농협은행");
        bankList.add("산업은행");
        bankList.add("수협은행");
        bankList.add("신한은행");
        bankList.add("씨티은행");
        bankList.add("외환은행");
        bankList.add("우리은행");
        bankList.add("하나은행");
        return bankList;
    }

    // 서점 목록 조회 요청
    @GetMapping("/list")
    public String List(Model model) {
        log.info("******* StoreController list");
        //DB에서 전체 게시글 데이터를 가져와서 List에 담아서 list.html로 전달
        List<StoreDTO> storeList = storeService.findAll();
        model.addAttribute("storeList", storeList);
        System.out.println("storeList = " + storeList);

        return "backend/store/list";
    }

    // 서점 상세
    @GetMapping("/{storeId}")
    public String detail(@PathVariable("storeId") Long storeId, Model model) {
        log.info("******* StoreController detail");
        StoreDTO store = new StoreDTO(storeService.getOneStore(storeId));
        model.addAttribute("store", store);
        return "backend/store/detail";
    }

    // 서점 상세 수정
    @GetMapping("/{storeId}/modify")
    public String modifyForm(@PathVariable("storeId") Long storeId, Model model) {
        log.info("******* StoreController modifyForm");
        StoreDTO store = new StoreDTO(storeService.getOneStore(storeId));
        model.addAttribute("store", store);

        log.info("******* StoreController modifyForm storeLoginId : {}", store.getStoreLoginId());
        log.info("******* StoreController modifyForm fileDir : {}", fileDir);

        return "backend/store/modify";
    }
    // 서점 상세 수정처리
    @PostMapping("/{storeId}/modify")
    public String modifyPro(@PathVariable("storeId") Long storeId, StoreForm storeForm) throws IOException {
        log.info("******* StoreController modifyPro - storeForm : {}", storeForm);
        log.info("******* StoreController modifyPro - storeId : {}", storeId);

        storeService.updateOneBoard(storeForm); // 저장/수정해~~
        return "redirect:/cms/store/{storeId}";
    }

    // 이미지 파일 화면에 보여주기 위한, 이미지 요청 uri. Resource = ...springframework.core.io... 임포트
    @ResponseBody // 이미지 데이터만 요청
    @GetMapping("/images/{fileName}")
    public Resource getImages(@PathVariable("fileName") String fileName) throws MalformedURLException {

        return new UrlResource("file:" + fileUploadService.getPath(fileName));
    }

}