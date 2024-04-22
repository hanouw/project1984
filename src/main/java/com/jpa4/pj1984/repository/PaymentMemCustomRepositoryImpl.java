package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.dto.PageRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor

public class PaymentMemCustomRepositoryImpl implements PaymentMemCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Qualifier("paymentBookHistoryCustomRepositoryImpl")
    private final PaymentBookHistoryCustomRepository paymentBookHistoryCustomRepository;

    @Override
    public List<PaymentMem> findMembershipListByStoreId(PageRequestDTO pageRequestDTO) {
        int offset = (pageRequestDTO.getPage() - 1) * pageRequestDTO.getSize();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PageRequestDTO dto = paymentBookHistoryCustomRepository.bindingMethod(pageRequestDTO);
        String order = dto.getDateOrder();
        String keyword = dto.getKeyword();
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (dto.getStartDate() != null) {
            startDate = LocalDate.parse(dto.getStartDate(), formatter).atStartOfDay();
            endDate = LocalDate.parse(dto.getEndDate(), formatter).atTime(LocalTime.MAX);
        }

        log.info("-------------PaymentMemCustomRepositoryImpl order:{}, keyword:{}, startDate:{}, endDate:{}", order, keyword, startDate, endDate);

        if (startDate == null && keyword == null ) { // 키워드 없음 + 기간 없음
            log.info("**************************키워드 없음 + 기간 없음");
            List<PaymentMem> historyList = em.createQuery("select p from PaymentMem p " +
                            "order by p.membershipStartDate "+ order +" ", PaymentMem.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            List<PaymentMem> historyList = em.createQuery("select p from PaymentMem p " +
                            "where p.membershipStartDate between :startDate And :endDate " +
                            "order by p.membershipStartDate "+ order +" ", PaymentMem.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String str = searchTypeMethod(pageRequestDTO);
            keyword = pageRequestDTO.getKeyword();
            List<PaymentMem> historyList = em.createQuery("select p from PaymentMem p " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "order by p.membershipStartDate "+ order +" ", PaymentMem.class)
                    .setParameter("keyword", keyword)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return historyList;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String str = searchTypeMethod(pageRequestDTO);
            keyword = pageRequestDTO.getKeyword();
            List<PaymentMem> historySerachList = em.createQuery("select p from PaymentMem p " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "and p.membershipStartDate between :startDate And :endDate  " +
                            "order by p.membershipStartDate " + order + " ", PaymentMem.class)
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
    public Long countMembershipListByStoreId(PageRequestDTO pageRequestDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PageRequestDTO dto = paymentBookHistoryCustomRepository.bindingMethod(pageRequestDTO);
        String order = dto.getDateOrder();
        String keyword = dto.getKeyword();
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (dto.getStartDate() != null) {
            startDate = LocalDate.parse(dto.getStartDate(), formatter).atStartOfDay();
            endDate = LocalDate.parse(dto.getEndDate(), formatter).atTime(LocalTime.MAX);
        }
        log.info("************PaymentMemCustomRepositoryImpl count page:{},order:{},keyword:{},startDate:{},endDate:{}",pageRequestDTO.getPage(),order,keyword,startDate,endDate);

        if (startDate == null && keyword == null) {
        log.info("**************************키워드 없음 + 기간 없음");
            Long result = (Long) em.createQuery("select count(p) from PaymentMem p " +
                            "order by p.membershipStartDate "+ order +" ")
                    .getSingleResult();
            log.info("--PaymentRepo result : {}", result);
            return result;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            Long result = (Long) em.createQuery("select count(p) from PaymentMem p " +
                            "where p.membershipStartDate between :startDate And :endDate " +
                            "order by p.membershipStartDate "+ order +" ")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
            return result;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String s = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(p) from PaymentMem p " +
                            "where and " + s + " like concat('%', :keyword, '%') " +
                            "order by p.membershipStartDate "+ order +" ")
                    .setParameter("keyword", keyword)
                    .getSingleResult();
            return result;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String s = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(p) from PaymentMem p " +
                            "where " + s + " like concat('%', :keyword, '%') " +
                            "and p.membershipStartDate between :startDate And :endDate  " +
                            "order by p.membershipStartDate " + order + " ")
                    .setParameter("keyword", keyword)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();

            return result;
        }
    }

    @Override
    public String searchTypeMethod(PageRequestDTO pageRequestDTO) {
        String searchType = pageRequestDTO.getSearchType();
        String s = "p";
        switch (searchType) {
            case "userId" :
                s += ".member.userId";
                break;
            case "userName" :
                s += ".member.userName";
                break;
            case "userPhoneNum" :
                s += ".member.userPhoneNum";
                break;
            case "userEmail" :
                s += ".member.userEmail";
                break;
            case "orderMembershipMethod" :
                s += ".orderMembershipMethod";
                break;
        }
        return s;
    }

}
