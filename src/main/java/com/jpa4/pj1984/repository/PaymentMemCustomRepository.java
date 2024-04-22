package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.dto.PageRequestDTO;

import java.util.List;

public interface PaymentMemCustomRepository {
    List<PaymentMem> findMembershipListByStoreId(PageRequestDTO pageRequestDTO);

    Long countMembershipListByStoreId(PageRequestDTO pageRequestDTO);

    String searchTypeMethod(PageRequestDTO pageRequestDTO);

}
