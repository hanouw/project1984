package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Answer;
import com.jpa4.pj1984.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
