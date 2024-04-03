package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.StoreLoginForm;
import org.springframework.stereotype.Repository;

public interface CmsCustomRepository {
    public Store findByStoreLoginId(String storeLoginId);
}
