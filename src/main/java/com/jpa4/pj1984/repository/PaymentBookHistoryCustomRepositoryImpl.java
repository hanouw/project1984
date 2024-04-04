package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Payment;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.dto.PageRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PaymentBookHistoryCustomRepositoryImpl implements PaymentBookHistoryCustomRepository{

    @PersistenceContext
    private EntityManager em;

    // storeId로 주문 목록 조회 판매자 ver
    @Override
    public List<PaymentBookHistory> findListByStoreId(Long storeId, PageRequestDTO pageRequestDTO) {
        int offset = (pageRequestDTO.getPage() - 1) * pageRequestDTO.getSize();
        if (pageRequestDTO.getSearchType() == null) {
            List<PaymentBookHistory> historyList = em.createQuery("select p from PaymentBookHistory p " +
                            "where p.book.storeId = :storeId " +
                            "order by p.orderBookHistoryId desc ", PaymentBookHistory.class)
                    .setParameter("storeId", storeId)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        } else {
            String s = method(pageRequestDTO);
            String keyword = pageRequestDTO.getKeyword();
            List<PaymentBookHistory> historySerachList = em.createQuery("select p from PaymentBookHistory p " +
                        "where p.book.storeId = :storeId and " + s + " like concat('%', :keyword, '%') " +
                        "order by p.orderBookHistoryId desc ", PaymentBookHistory.class)
                .setParameter("storeId", storeId)
                .setParameter("keyword", keyword)
                .setFirstResult(offset)
                .setMaxResults(pageRequestDTO.getSize())
                .getResultList();
            return historySerachList;
        }
    }

    @Override
    public String method(PageRequestDTO pageRequestDTO) {
        String searchType = pageRequestDTO.getSearchType();
        String s = "p";
        switch (searchType) {
            case "orderNo" :
                s += ".payment.orderBookNo";
                break;
            case "userId" :
                s += ".payment.member.userId";
                break;
            case "userName" :
                s += ".payment.member.userName";
                break;
            case "isbn" :
                s += ".book.isbn";
                break;
            case "bookTitle" :
                s += ".book.bookTitle";
                break;
            case "storeTitle" :
                s += ".book.storeTitle";
                break;
            case "status" :
                s += ".payment.paymentBookStatus";
                break;
            case "method" :
                s += ".payment.orderBookMethod";
                break;
        }
        return s;
    }
}
