package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.dto.PageRequestDTO;

import java.util.List;

public interface PaymentMemCustomRepository {
    List<PaymentMem> findMembershipList(PageRequestDTO pageRequestDTO);

    Long countMembershipList(PageRequestDTO pageRequestDTO);

    String searchTypeMethod(PageRequestDTO pageRequestDTO);

}
