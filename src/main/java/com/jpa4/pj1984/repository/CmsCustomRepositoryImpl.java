package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.StoreLoginForm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CmsCustomRepositoryImpl implements CmsCustomRepository{

//    JPA (Java Persistence API)에서 EntityManager 객체를 주입하는 데 사용
//    EntityManager는 영속성 컨텍스트를 관리하고 데이터베이스 작업을 수행하는 데 사용
    @PersistenceContext
    private EntityManager em;

    // 서점 로그인 아이디로 서점 한개 조회
    @Override
    public Store findByStoreLoginId(String storeLoginId) {
        Store store = em.createQuery("select s from Store s where s.storeLoginId = :storeLoginId", Store.class) // , 이름.타입) 형태로
                .setParameter("storeLoginId", storeLoginId)
                .getSingleResult();
        log.info("******* CmsCustomRepository : store = {}", store);
        return store;
    }

}
