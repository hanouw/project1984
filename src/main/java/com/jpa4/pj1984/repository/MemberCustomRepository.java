package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Member;

public interface MemberCustomRepository {
    Member findByUserId(String userId);
}
