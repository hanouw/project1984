package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Book;
import com.jpa4.pj1984.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // userId 로 회원 한명 조회
    public Member findByUserId(String userId);

    //Page로 조회
    Page<Member> findByUserNameContaining(String keyword, Pageable pageable);
    Page<Member> findByUserIdContaining(String keyword, Pageable pageable);
}
