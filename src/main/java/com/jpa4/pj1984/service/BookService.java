package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.dto.BookCategoryDTO;
import com.jpa4.pj1984.dto.BookDTO;
import com.jpa4.pj1984.dto.BookForm;
import com.jpa4.pj1984.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final FileUploadService fileUploadService;

    //저장
    public void save(BookForm bookForm) throws IOException{
        String projectPath = System.getProperty("user.dir") + "";

        ProductFile bookFile = fileUploadService.saveFile(bookForm.getBookFile());
        ProductFile imgFile = fileUploadService.saveFile(bookForm.getBookImg());
        Book entity = bookForm.toEntity();
        entity.setBookFileOrg(bookFile.getOrgFileName());
        entity.setBookFileStored(bookFile.getStoredFileName());
        entity.setBookImgOrg(imgFile.getOrgFileName());
        entity.setBookImgStored(imgFile.getStoredFileName());
        // img
        Book bookSaved = bookRepository.save(entity);
    }

    //목록조회(검색title)
    public Page<BookDTO> findByBookTitleContaining(String keyword, Pageable pageable) {
        Page<Book> all = bookRepository.findByBookTitleContaining(keyword, pageable);
        Page<BookDTO> list = all.map(b -> new BookDTO(b));
        return list;
    }
    //목록조회(검색isbn)
    public Page<BookDTO> findByIsbnContaining(String keyword, Pageable pageable) {
        Page<Book> all = bookRepository.findByIsbnContaining(keyword, pageable);
        Page<BookDTO> list = all.map(b -> new BookDTO(b));
        return list;
    }

    //목록조회(일반)
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> all = bookRepository.findAll(pageable);
        Page<BookDTO> list = all.map(b -> new BookDTO(b));
        return list;
    }

    //--스토어만 구분--//
    //목록조회(검색title)
//    public Page<BookDTO> findByBookTitleContainingAndStoreId(String keyword, Long storeId, Pageable pageable) {
//        Page<Book> all = bookRepository.findByBookTitleContainingAndStoreId(keyword, storeId, pageable);
//        Page<BookDTO> list = all.map(b -> new BookDTO(b));
//        return list;
//    }
//    //목록조회(검색isbn)
//    public Page<BookDTO> findByIsbnContainingAndStoreId(String keyword, Long storeId, Pageable pageable) {
//        Page<Book> all = bookRepository.findByIsbnContainingAndStoreId(keyword, storeId, pageable);
//        Page<BookDTO> list = all.map(b -> new BookDTO(b));
//        return list;
//    }
//
//    //목록조회(일반)
//    public Page<BookDTO> findAllByStoreId(Long storeId, Pageable pageable) {
//        Page<Book> all = bookRepository.findAllByStoreId(storeId, pageable);
//        Page<BookDTO> list = all.map(b -> new BookDTO(b));
//        return list;
//    }




    //목록조회(LIST)
//    public List<BookDTO> findAll() {
//        List<Book> all = bookRepository.findAll();
//        List<BookDTO> list = all.stream()
//                .map(b -> new BookDTO(b))
//                .collect(Collectors.toList());
//        return list;
//    }

    //조회(1개)
    public BookDTO findOne(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return new BookDTO(book);
    }

    //수정
    public void updateOne(BookForm bookForm) throws IOException{
        Book book = bookRepository.findById(bookForm.getBookId()).orElse(null);

        if (bookForm. getBookImg() != null) {
            if (book != null && !book.getBookImgStored().isEmpty()){
                boolean deleteImg = fileUploadService.deleteFile(book.getBookImgStored());
                System.out.println("북커버이미지 삭제");
                if (deleteImg){
                    ProductFile bookImg = fileUploadService.saveFile(bookForm.getBookImg());
                    book.setBookImgOrg(bookImg.getOrgFileName());
                    book.setBookImgStored(bookImg.getStoredFileName());
                }
            }
        }
        if (bookForm. getBookFile() != null) {
            if (book != null && !book.getBookFileStored().isEmpty()){
                boolean deleteFile = fileUploadService.deleteFile(book.getBookFileStored());
                System.out.println("북파일 삭제");
                if (deleteFile){
                    ProductFile bookFile = fileUploadService.saveFile(bookForm.getBookFile());
                    book.setBookFileOrg(bookFile.getOrgFileName());
                    book.setBookFileStored(bookFile.getStoredFileName());
                }
            }
        }

        // 저장
        book.setIsbn(bookForm.getIsbn());
        book.setBookTitle(bookForm.getBookTitle());
        book.setStore(bookForm.getStore());
        book.setBookPub(bookForm.getBookPub());
        book.setBookPaperPrice(bookForm.getBookPaperPrice());
        book.setBookEbookPrice(bookForm.getBookEbookPrice());
        book.setBookCategory(bookForm.getBookCategory());
        book.setBookIntro(bookForm.getBookIntro());
        book.setBookIndex(bookForm.getBookIndex());
        book.setBookReview(bookForm.getBookReview());
        book.setBookWriter(bookForm.getBookWriter());
        book.setBookWriterProfile(bookForm.getBookWriterProfile());
        System.out.println("도서서비스실행 - 처리 - 됬습니둥");
    }

    //VIEW 목록 조회용
    public List<BookDTO> findAllList() {
        List<Book> all = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(Book list : all) {
            bookDTOList.add(new BookDTO(list));
        }
        return bookDTOList;
    }
}
