package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository{

//    JPA (Java Persistence API)에서 EntityManager 객체를 주입하는 데 사용
//    EntityManager는 영속성 컨텍스트를 관리하고 데이터베이스 작업을 수행하는 데 사용
    @PersistenceContext
    private EntityManager em;

    @Override
    public Member findByUserId(String userId) {
        Member member = em.createQuery("select m from Member m where m.userId = :userId", Member.class) // 마지막에 , 뒷부분 = 이름.타입) 형태로
                .setParameter("userId", userId)
                .getSingleResult();
        log.info("******* MemberCustomRepository : member = {}", member);
        return member;
    }

}
