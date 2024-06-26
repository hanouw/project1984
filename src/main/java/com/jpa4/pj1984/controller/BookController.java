package com.jpa4.pj1984.controller;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.domain.BookCategoryStatus;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.security.domain.CustomCms;
import com.jpa4.pj1984.service.BookCategoryService;
import com.jpa4.pj1984.service.BookService;
import com.jpa4.pj1984.service.FileUploadService;
import com.jpa4.pj1984.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/cms")
@RequiredArgsConstructor
public class BookController {
    //--공통사용-----------------------------------------------------//
    private final BookService bookService;
    private final BookCategoryService bookCategoryService;
    private final StoreService storeService;
    private final FileUploadService fileUploadService;

    // 도서 카테고리 리스트 요청
    @ModelAttribute("categoryLists")
    public List<BookCategoryDTO> categoryLists(){
        List<BookCategoryDTO> bookCategoryDTOList = bookCategoryService.findCategoryAllList();
        log.info("****BookController bookCategoryDTOList :{} ", bookCategoryDTOList.size());
        return bookCategoryDTOList;
    }

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
    
    //--상품관리-----------------------------------------------------//

    //상품추가폼
    @GetMapping("/book/add")
    public String bookAddForm(@ModelAttribute BookForm bookForm, Model model){
        log.info("--CMS--Book--AddForm--Request--");
        return "backend/book/add";
    }
    //상품추가
    @PostMapping("/book/add")
    public String bookAddPro(BookForm bookForm) throws Exception{
        log.info("--CMS--Book-Add--Request-- bookForm : {}", bookForm);
        bookService.save(bookForm);
        return "redirect:/cms/book/list";
    }
    //상품리스트
//    @GetMapping("/book/list")
//    public String bookList(Model model,
//                           @PageableDefault(page = 0, size = 10, sort = "bookId", direction = Sort.Direction.DESC)
//                           Pageable pageable,
//                           String keyword,
//                           String selectOption) {
//        log.info("--CMS--Book--List--Request--");
//        Page<BookDTO> bookDTOList = null; // 초기화
//        if(keyword == null || keyword.isEmpty()){
//            bookDTOList = bookService.findAll(pageable);
//        }else if("bookTitle".equals(selectOption)){
//            bookDTOList = bookService.findByBookTitleContaining(keyword, pageable);
//        }else if("isbn".equals(selectOption)){
//            bookDTOList = bookService.findByIsbnContaining(keyword, pageable);
//        }
//
//        // 페이징 처리 변수 지정
//        int nowPage = bookDTOList.getPageable().getPageNumber() + 1;
//        int prevPage = Math.max(nowPage -1, 1);
//        int nextPage = Math.min(nowPage +1, bookDTOList.getTotalPages());
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, bookDTOList.getTotalPages());
//
//        // HTML VIEW 페이지로 데이터 전달
//        model.addAttribute("bookList", bookDTOList);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "backend/book/list";
//    }

    // 상품리스트(ADMIN/USER 다른 목록 노출 코드 체크 -> 필요)
    @GetMapping("/book/list")
    public String bookList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "bookId", direction = Sort.Direction.DESC)
                           Pageable pageable,
                           @AuthenticationPrincipal CustomCms customCms,
                           String keyword,
                           String selectOption
                           ) {
        log.info("--CMS--Book--List--Request--");
        Page<BookDTO> bookDTOList = null; // 초기화
        String username = customCms.getUsername();
        Long storeId = customCms.getStore().getStoreId();

        System.out.println("username = " + username);
        System.out.println("storeId = " + storeId);
        if(username.equals("admin")){
            System.out.println("admin으로 들어옴!");
            if(keyword == null || keyword.isEmpty()){
                bookDTOList = bookService.findAll(pageable);
            }else if("bookTitle".equals(selectOption)){
                bookDTOList = bookService.findByBookTitleContaining(keyword, pageable);
            }else if("isbn".equals(selectOption)){
                bookDTOList = bookService.findByIsbnContaining(keyword, pageable);
            }
        }
        else{
            System.out.println("user02로 들어옴");
            if(keyword == null || keyword.isEmpty()){
                bookDTOList = bookService.findAllByStoreId(storeId, pageable);
            }else if("bookTitle".equals(selectOption)){
                bookDTOList = bookService.findByBookTitleContainingAndStoreId(keyword, storeId, pageable);
            }else if("isbn".equals(selectOption)){
                bookDTOList = bookService.findByIsbnContainingAndStoreId(keyword, storeId, pageable);
            }
        }

        // 페이징 처리 변수 지정
        int nowPage = bookDTOList.getPageable().getPageNumber() + 1;
        int prevPage = Math.max(nowPage -1, 1);
        int nextPage = Math.min(nowPage +1, bookDTOList.getTotalPages());
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, bookDTOList.getTotalPages());

        // HTML VIEW 페이지로 데이터 전달
        model.addAttribute("bookList", bookDTOList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "backend/book/list";
    }


    //상품리스트(LIST)
//    @GetMapping("/book/list")
//    public String bookList(Model model){
//        log.info("--CMS--Book--List--Request--");
//        List<BookDTO> bookDTOList = bookService.findAll();
//        model.addAttribute("bookList", bookDTOList);
//        System.out.println("bookDTOList = " + bookDTOList);
//        return "backend/book/list";
//    }

    //상품상세
    @GetMapping("/book/{id}")
    public String bookDetail(@PathVariable("id") Long id, Model model){
        BookDTO book = bookService.findOne(id);
        System.out.println("book = " + book);
        model.addAttribute("book", book);
        return "backend/book/detail";
    }

    //상품수정
    @GetMapping("/book/{id}/modify")
    public String bookModifyForm(@PathVariable("id") Long id, Model model){
        BookDTO book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "backend/book/modify";
    }
    @PostMapping("/book/{id}/modify")
    public String bookModifyPro(@PathVariable("id") Long id, BookForm bookForm) throws Exception{
        System.out.println("실행확인-도서수정 id = " + id + ", bookForm = " + bookForm);
        bookService.updateOne(bookForm);
        return "redirect:/cms/book/{id}";
    }

//--상품카테고리관리-----------------------------------------------------//
    
    //상품카테고리추가폼
    @GetMapping("/bookCategory/add")
    public String bookCategoryAddForm(@ModelAttribute BookCategoryForm bookCategoryForm, Model model){
        log.info("--CMS--Book--Category--AddForm--Request--");
        return "backend/bookcategory/add";
    }

    //상품카테고리추가
    @PostMapping("/bookCategory/add")
    public String bookCategoryAddPro(BookCategoryForm bookCategoryForm){
        log.info("--CMS--Book--Category--Add--Request--");
        Long save = bookCategoryService.save(bookCategoryForm);
        return "redirect:/cms/bookCategory/list";
    }

    //상품카테고리리스트
    @GetMapping("/bookCategory/list")
    public String bookCategoryList(Model model,
                                   @PageableDefault(page = 0, size = 10, sort = "bookCategoryId", direction = Sort.Direction.DESC)
                                   Pageable pageable,
                                   String keyword) {
        log.info("--CMS--Book--Category--List--Request--");
        Page<BookCategoryDTO> bookCategoryDTOList = null;
         // DB에서 전체 게시글 데이터를 가져와서 bookCategoryList 변수에 저장
        if(keyword == null || keyword.isEmpty()){
            bookCategoryDTOList = bookCategoryService.findAll(pageable);
        }else{
            bookCategoryDTOList = bookCategoryService.findByBookCategoryNameContaining(keyword, pageable);
        }

        // 페이징 처리 변수 지정
        int nowPage = bookCategoryDTOList.getPageable().getPageNumber() + 1;
        int prevPage = Math.max(nowPage -1, 1);
        int nextPage = Math.min(nowPage +1, bookCategoryDTOList.getTotalPages());
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, bookCategoryDTOList.getTotalPages());

        // HTML VIEW 페이지로 데이터 전달
        model.addAttribute("bookCategoryList", bookCategoryDTOList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "backend/bookcategory/list";
    }

    //상품카테고리리스트
//    @GetMapping("/bookCategory/list")
//    public String bookCategoryList(Model model){
//        log.info("--CMS--Book--Category--List--Request--");
//        //DB에서 전체 게시글 데이터를 가져와서 bookCategoryList에 담아서 list.html로 전달
//        List<BookCategoryDTO> bookCategoryDTOList = bookCategoryService.findAll();
//        model.addAttribute("bookCategoryList", bookCategoryDTOList);
//        System.out.println("bookCategoryDTOList = " + bookCategoryDTOList);
//        return "backend/bookcategory/list";
//    }

    //상품카테고리상세
    @GetMapping("/bookCategory/{id}")
    public String bookCategoryDetail(@PathVariable("id") Long id, Model model){
        BookCategoryDTO bookCategory = bookCategoryService.findOne(id);
        System.out.println("bookCategory = " + bookCategory);
        model.addAttribute("bookCategory", bookCategory);
        return "backend/bookcategory/detail";
    }

    //상품카테고리수정
    @GetMapping("/bookCategory/{id}/modify")
    public String bookCategoryModifyForm(@PathVariable("id") Long id, Model model){
        BookCategoryDTO bookCategory = bookCategoryService.findOne(id);
        model.addAttribute("bookCategory", bookCategory);
        return "backend/bookcategory/modify";
    }
    @PostMapping("/bookCategory/{id}/modify")
    public String bookCategoryModifyPro(@PathVariable("id") Long id, BookCategoryForm bookCategoryForm){
        bookCategoryService.updateOne(bookCategoryForm);
        return "redirect:/cms/bookCategory/{id}";
    }

    // 이미지 데이터 요청
    @ResponseBody
    @GetMapping("/bookimages/{fileName}")
    public Resource getImages(@PathVariable("fileName") String fileName) throws MalformedURLException{
        System.out.println("북컨트롤러-fileName = " + fileName);
        return new UrlResource("file:" + fileUploadService.getPath(fileName));

    }

}
