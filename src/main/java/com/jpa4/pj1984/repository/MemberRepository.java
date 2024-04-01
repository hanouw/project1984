package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
