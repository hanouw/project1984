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
@RequestMapping("/store")
public class StoreController {

    @Value("${file.dir}")
    private String fileDir;

    private final StoreService storeService;
    private final FileUploadService fileUploadService;


    @ModelAttribute("bankList")
    public List<String> banks() {
        log.info("************** colors 데이터 전송!");
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
    public String list() {
        log.info("******* StoreController list");
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
        return "redirect:/store/{storeId}";
    }

    // 이미지 파일 화면에 보여주기 위한, 이미지 요청 uri. Resource = ...springframework.core.io... 임포트
    @ResponseBody // 이미지 데이터만 요청
    @GetMapping("/images/{fileName}")
    public Resource getImages(@PathVariable("fileName") String fileName) throws MalformedURLException {

        return new UrlResource("file:" + fileUploadService.getPath(fileName));
    }

}

    /*
    // 글 수정
    @GetMapping("/{id}/modify")
    // @PreAuthorize("isAuthenticated()")
    public String modifyForm(@PathVariable("id") Long id, Model model) {
        log.info("**** BoardController GET /boards/:id/modify - id : {}", id);
        BoardDTO board = boardService.getOneBoard(id);
        model.addAttribute("board", board);
        return "board/modify";
    }
    @PostMapping("/{id}/modify")
    // @PreAuthorize("principal.username == #boardForm.writer")
    public String modifyPro(@PathVariable("id") Long id, BoardForm boardForm) {
        log.info("**** BoardController GET /boards/:id/modify - id : {}", id);
        log.info("**** BoardController GET /boards/:id/modify - boardForm : {}", boardForm);
        boardService.updateOneBoard(boardForm);
        return "redirect:/boards/{id}";
    }
    /*
    // 서점 상세
    @GetMapping("/detail")
    public String detailForm(@ModelAttribute("store") StoreForm storeForm, Model model) {
        log.info("******* StoreController");
        Store findStore = storeService.getOneStore(storeForm.getStoreId());
        if(findStore == null) {
            return "backend/store/add";
        }else {
            model.addAttribute("store", findStore);
            return "backend/store/detail";
        }
    }

}
/*
    // 게시판 목록
    @GetMapping("/list") // ...8080/boards/list?page=2&size=10 (1페이지부터시작)
    public String list(Model model, PageRequestDTO pageRequestDTO) { // page, size
        log.info("**** BoardController GET /boards/list");
        //List<BoardDTO> list = boardService.getList();
        Page<Board> result = boardService.getListWithPaging(pageRequestDTO);
        // 글목록 List<BoardDTO> 보내기
        List<Board> contents = result.getContent(); // Entity 목록이 list에 담긴형태
        //  Entity -> BoardDTO 변환해서 view에 전달
        List<BoardDTO> list = new ArrayList<>();
        for(int i = 0; i < contents.size(); i++) {
            Board board = contents.get(i); // Entity한개 꺼내서
            BoardDTO dto = new BoardDTO(board); // BoardDTO로 변환
            list.add(dto); // list에 추가
        }
            /* 위 for문을 Stream을 이용한 버전
            List<BoardDTO> list = contents.stream()
                    .map(b -> new BoardDTO(b))
                    .collect(Collectors.toList());

        // 페이징처리 위한 PageResponseDTO 보내기
        PageResponseDTO pageResponseDTO = new PageResponseDTO(pageRequestDTO, result.getTotalElements());

        model.addAttribute("list", list); // 글 목록 view에 전달
        model.addAttribute("pageDTO", pageResponseDTO); // 페이징처리 전달
        return "board/list";
    }
    // 상세 페이지
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        log.info("**** BoardController GET /boards/:id - id : {}", id);
        BoardDTO board = boardService.getOneBoard(id);
        model.addAttribute("board", board);
        return "board/detail";
    }
    // 글 수정
    @GetMapping("/{id}/modify")
    // @PreAuthorize("isAuthenticated()")
    public String modifyForm(@PathVariable("id") Long id, Model model) {
        log.info("**** BoardController GET /boards/:id/modify - id : {}", id);
        BoardDTO board = boardService.getOneBoard(id);
        model.addAttribute("board", board);
        return "board/modify";
    }
    @PostMapping("/{id}/modify")
    // @PreAuthorize("principal.username == #boardForm.writer")
    public String modifyPro(@PathVariable("id") Long id, BoardForm boardForm) {
        log.info("**** BoardController GET /boards/:id/modify - id : {}", id);
        log.info("**** BoardController GET /boards/:id/modify - boardForm : {}", boardForm);
        boardService.updateOneBoard(boardForm);
        return "redirect:/boards/{id}";
    }



}*/
