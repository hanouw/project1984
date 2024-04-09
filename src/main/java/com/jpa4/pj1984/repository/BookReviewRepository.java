package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
}
