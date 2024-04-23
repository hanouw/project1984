package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByBookTitleContaining(String keyword, Pageable pageable);
    Page<Book> findByIsbnContaining(String keyword, Pageable pageable);

    // 상품리스트(ADMIN/USER 다른 목록 노출 코드 체크 -> 필요)
    @Query("select b from Book b where b.store.storeId = :storeId and b.bookTitle like concat('%', :keyword, '%')")
    Page<Book> findByBookTitleWithAndStoreId(@Param("keyword") String keyword, @Param("storeId") Long storeId, Pageable pageable);

    @Query("select b from Book b where b.store.storeId = :storeId and b.isbn like concat('%', :keyword, '%')")
    Page<Book> findByIsbnWithAndStoreId(@Param("keyword") String keyword, @Param("storeId") Long storeId, Pageable pageable);

    @Query("select b from Book b where b.store.storeId = :storeId")
    Page<Book> findAllWithStoreId(@Param("storeId") Long storeId, Pageable pageable);

}
