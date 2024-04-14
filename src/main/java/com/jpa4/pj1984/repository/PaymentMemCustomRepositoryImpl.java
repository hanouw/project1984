package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.dto.PageRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PaymentMemCustomRepositoryImpl implements PaymentMemCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PaymentMem> findMembershipListByStoreId(Long storeId, PageRequestDTO pageRequestDTO) {
        int offset = (pageRequestDTO.getPage() - 1) * pageRequestDTO.getSize();
        String order = pageRequestDTO.getDateOrder();
        String keyword = null;
        String searchType = null;
        String period = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        log.info("----PaymentMemRepo period:{}", pageRequestDTO.getDatePeriod());

        if (pageRequestDTO.getDatePeriod() != null) {
            if (pageRequestDTO.getDatePeriod().equals("today")) {
                //            LocalDateTime today = LocalDateTime.now();
                //            startDate = today;
                //            endDate = today;
                startDate = LocalDateTime.parse("2024-04-07T00:00:00");
                endDate = LocalDateTime.parse("2024-04-07T00:00:00");
            } else if (pageRequestDTO.getDatePeriod().equals("oneWeek")) {
                startDate = LocalDateTime.parse("2024-04-06T00:00:00");
                endDate = LocalDateTime.parse("2024-04-07T00:00:00");
            } else if (pageRequestDTO.getDatePeriod().equals("oneMonth")) {
                startDate = LocalDateTime.parse("2024-04-05T00:00:00");
                endDate = LocalDateTime.parse("2024-04-07T00:00:00");
            }
        }
        log.info("***************PaymentMemRepo startDate:{} endDate:{}", startDate, endDate);
        log.info("--------PaymentMemRepo getStartDateSelected:{} getEndDateSelected:{}", pageRequestDTO.getStartDateSelected(), pageRequestDTO.getEndDateSelected());

        if (pageRequestDTO.getStartDateSelected() != null && pageRequestDTO.getEndDateSelected() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            startDate = LocalDate.parse(pageRequestDTO.getStartDateSelected(), formatter).atStartOfDay();
            endDate = LocalDate.parse(pageRequestDTO.getEndDateSelected(), formatter).atStartOfDay();
        }

        if (startDate == null && endDate == null && period == null && pageRequestDTO.getKeyword() == null ) { // 키워드 없음 + 기간 없음
            log.info("**************************키워드 없음 + 기간 없음");
            List<PaymentMem> historyList = em.createQuery("select p from PaymentMem p " +
                            "where p.store.storeId = :storeId " +
                            "order by p.membershipStartDate "+ order +" ", PaymentMem.class)
                    .setParameter("storeId", storeId)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        }
        else if (pageRequestDTO.getKeyword() == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            List<PaymentMem> historyList = em.createQuery("select p from PaymentMem p " +
                            "where p.store.storeId = :storeId and p.membershipStartDate between :startDate And :endDate " +
                            "order by p.membershipStartDate "+ order +" ", PaymentMem.class)
                    .setParameter("storeId", storeId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        }
        else if (startDate == null && pageRequestDTO.getKeyword() != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String s = method(pageRequestDTO);
            keyword = pageRequestDTO.getKeyword();
            List<PaymentMem> historyList = em.createQuery("select p from PaymentMem p " +
                            "where p.store.storeId = :storeId and " + s + " like concat('%', :keyword, '%') " +
                            "order by p.membershipStartDate "+ order +" ", PaymentMem.class)
                    .setParameter("storeId", storeId)
                    .setParameter("keyword", keyword)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String s = method(pageRequestDTO);
            keyword = pageRequestDTO.getKeyword();
            List<PaymentMem> historySerachList = em.createQuery("select p from PaymentMem p " +
                            "where p.store.storeId = :storeId and " + s + " like concat('%', :keyword, '%') " +
                            "and p.membershipStartDate between :startDate And :endDate  " +
                            "order by p.membershipStartDate " + order + " ", PaymentMem.class)
                    .setParameter("storeId", storeId)
                    .setParameter("keyword", keyword)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();

            return historySerachList;
        }
    }

    @Override
    public Long countMembershipListByStoreId(Long storeId, PageRequestDTO pageRequestDTO) {
        int offset = (pageRequestDTO.getPage() - 1) * pageRequestDTO.getSize();
        String order = pageRequestDTO.getDateOrder();
        if (pageRequestDTO.getKeyword() == null) {
            Long result = (Long) em.createQuery("select count(p) from PaymentMem p " +
                            "where p.store.storeId = :storeId " +
                            "order by p.membershipStartDate "+ order +" ")
                    .setParameter("storeId", storeId)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getSingleResult();
            log.info("--PaymentRepo result : {}", result);
            return result;
        }
        else {
            String s = method(pageRequestDTO);
            String keyword = pageRequestDTO.getKeyword();
            Long searchResult = (Long) em.createQuery("select count(p) from PaymentMem p " +
                            "where p.store.storeId = :storeId and " + s + " like concat('%', :keyword, '%')" +
                            "order by p.membershipStartDate "+ order +" ")
                    .setParameter("storeId", storeId)
                    .setParameter("keyword", keyword)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getSingleResult();
            log.info("--PaymentRepo result : {}", searchResult);
            return searchResult;
        }
    }

    @Override
    public String method(PageRequestDTO pageRequestDTO) {
        String searchType = pageRequestDTO.getSearchType();
        String s = "p";
        switch (searchType) {
            case "orderNo" :
                s += ".paymentBook.orderBookNo";
                break;
            case "userId" :
                s += ".paymentBook.member.userId";
                break;
            case "userName" :
                s += ".paymentBook.member.userName";
                break;
            case "bookId" :
                s += ".book.bookId";
                break;
            case "bookTitle" :
                s += ".book.bookTitle";
                break;
            case "storeTitle" :
                s += ".book.storeTitle";
                break;
            case "status" :
                s += ".paymentBook.paymentBookStatus";
                break;
            case "method" :
                s += ".paymentBook.orderBookMethod";
                break;
        }
        return s;
    }
}
