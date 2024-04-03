package com.jpa4.pj1984.controller;



import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    // 서점 신규 등록
    @PostMapping("/add")
    public String addPro(StoreForm storeForm) {
        log.info("******* StoreController addPro");

        // storeService.save(storeForm, "java01");
        return "redirect:/store/list";
    }

    // 서점 상세 수정
    @PostMapping("/modify")
    public String modifyPro(StoreForm storeForm) {
        log.info("******* StoreController addPro");

        // storeService.save(storeForm, "java01");
        return "redirect:/store/list";
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
