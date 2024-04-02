package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
