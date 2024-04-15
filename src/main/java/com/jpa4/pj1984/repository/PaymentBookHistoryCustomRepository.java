package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.dto.PageRequestDTO;

import java.util.List;

public interface PaymentBookHistoryCustomRepository {
    // storeId로 주문 목록 조회 판매자 ver
    List<PaymentBookHistory> findBookListByStoreId(Long storeId, PageRequestDTO pageRequestDTO);

    Long countBookListByStoreId(Long storeId, PageRequestDTO pageRequestDTO);

    String searchTypeMethod(PageRequestDTO pageRequestDTO);

    PageRequestDTO bindingMethod(PageRequestDTO pageRequestDTO);
}
