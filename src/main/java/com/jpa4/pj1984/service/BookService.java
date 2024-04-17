package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.dto.BookDTO;
import com.jpa4.pj1984.dto.BookForm;
import com.jpa4.pj1984.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    //목록조회
    public List<BookDTO> findAll() {
        List<Book> all = bookRepository.findAll();
        List<BookDTO> list = all.stream()
                .map(b -> new BookDTO(b))
                .collect(Collectors.toList());
        return list;
    }

    //조회(1개)
    public BookDTO findOne(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return new BookDTO(book);
    }

    //수정
    public void updateOne(BookForm bookForm) throws IOException{
        Book book = bookRepository.findById(bookForm.getBookId()).orElse(null);
        if(!bookForm.getBookImg().isEmpty()){ // 북 이미지
            ProductFile bookImg = fileUploadService.saveFile(bookForm.getBookImg());
            book.setBookImgOrg(bookImg.getOrgFileName());
            book.setBookImgStored(bookImg.getStoredFileName());
        }
        if(!bookForm.getBookFile().isEmpty()){ // 북 파일
            ProductFile bookFile = fileUploadService.saveFile(bookForm.getBookFile());
            book.setBookFileOrg(bookFile.getOrgFileName());
            book.setBookFileStored(bookFile.getStoredFileName());
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
