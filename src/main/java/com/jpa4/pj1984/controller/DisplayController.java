package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.dto.BookDTO;
import com.jpa4.pj1984.dto.DisplayDTO;
import com.jpa4.pj1984.dto.DisplayForm;
import com.jpa4.pj1984.dto.StoreDTO;
import com.jpa4.pj1984.service.BookService;
import com.jpa4.pj1984.service.DisplayService;
import com.jpa4.pj1984.service.FileUploadService;
import com.jpa4.pj1984.service.StoreService;
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
public class DisplayController {
    //--공통사용-----------------------------------------------------//
    private final DisplayService displayService;
    private final BookService bookService;
    private final StoreService storeService;
    private final FileUploadService fileUploadService;

    // 도서 리스트 요청
    @ModelAttribute("BookLists")
    public List<BookDTO> bookLists(){
        List<BookDTO> bookDTOList = bookService.findAllList();
        log.info("****BookController bookCategoryDTOList :{} ", bookDTOList.size());
        return bookDTOList;
    }

    // 스토어 리스트 요청
    @ModelAttribute("storeLists")
    public List<StoreDTO> storeLists(){
        List<StoreDTO> storeDTOList = storeService.findStoreAllList();
        System.out.println("storeDTOList = " + storeDTOList);
        return storeDTOList;
    }

    // 이미지 데이터 요청
    @ResponseBody
    @GetMapping("/displayimages/{fileName}")
    public Resource getImages(@PathVariable("fileName") String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileUploadService.getPath(fileName));
    }

    //--전시관리-----------------------------------------------------//

    // 전시 상세
    @GetMapping("/display/detail")
    public String displayDetail(Model model) {
        DisplayDTO display = displayService.findOne();
        model.addAttribute("display", display);
        return "backend/display/detail";
    }

    // 전시 수정
    @GetMapping("/display/modify")
    public String displayModifyForm(Model model) {
        log.info("******* DisplayController modify");
        DisplayDTO display = displayService.findOne();
        model.addAttribute("display", display);
        return "backend/display/modify";
    }
    // 전시 수정처리
    @PostMapping("/display/modify")
    public String displayModifyPro(DisplayForm displayForm) {
        log.info("******* DisplayController modify");
        displayService.updateOne(displayForm);
        return "redirect:/cms/display/detail";
    }





}
