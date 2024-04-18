package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

}
