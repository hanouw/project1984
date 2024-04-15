package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.dto.PageRequestDTO;

import java.util.List;

public interface PaymentMemCustomRepository {
    List<PaymentMem> findMembershipListByStoreId(Long storeId, PageRequestDTO pageRequestDTO);

    Long countMembershipListByStoreId(Long storeId, PageRequestDTO pageRequestDTO);

    String method(PageRequestDTO pageRequestDTO);
}
