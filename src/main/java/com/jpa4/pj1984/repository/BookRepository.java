package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByBookTitleContaining(String keyword, Pageable pageable);
    Page<Book> findByIsbnContaining(String keyword, Pageable pageable);

    // 상품리스트(ADMIN/USER 다른 목록 노출 코드 체크 -> 필요)
//    Page<Book> findByBookTitleContainingAndStoreId(String keyword, Long storeId, Pageable pageable);
//    Page<Book> findByIsbnContainingAndStoreId(String keyword, Long storeId, Pageable pageable);
}
