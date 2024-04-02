package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsRepository extends JpaRepository<Store, Long> {
}
