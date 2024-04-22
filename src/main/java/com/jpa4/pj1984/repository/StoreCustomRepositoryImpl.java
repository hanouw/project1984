package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.PageRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor

public class StoreCustomRepositoryImpl implements StoreCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Qualifier("paymentBookHistoryCustomRepositoryImpl")
    private final PaymentBookHistoryCustomRepository paymentBookHistoryCustomRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<Store> findStoreList(PageRequestDTO pageRequestDTO) {
        int offset = (pageRequestDTO.getPage() - 1) * pageRequestDTO.getSize();

        PageRequestDTO dto = paymentBookHistoryCustomRepository.bindingMethod(pageRequestDTO);
        String order = dto.getDateOrder();
        String keyword = dto.getKeyword();
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (dto.getStartDate() != null) {
            startDate = LocalDate.parse(dto.getStartDate(), formatter).atStartOfDay();
            endDate = LocalDate.parse(dto.getEndDate(), formatter).atTime(LocalTime.MAX);
        }

        log.info("-------------PaymentBookHistoryRepo order:{}, keyword:{}, startDate:{}, endDate:{}", order, keyword, startDate, endDate);


        if (startDate == null && keyword == null ) { // 키워드 없음 + 기간 없음
            log.info("**************************키워드 없음 + 기간 없음");
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "order by s.createDate "+ order +" ", Store.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "where s.createDate between :startDate And :endDate " +
                            "order by s.createDate "+ order +" ", Store.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String str = searchTypeMethod(pageRequestDTO);
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "order by s.createDate "+ order +" ", Store.class)
                    .setParameter("keyword", keyword)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String str = searchTypeMethod(pageRequestDTO);
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "and s.createDate between :startDate And :endDate  " +
                            "order by s.createDate " + order + " ", Store.class)
                    .setParameter("keyword", keyword)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();

            return storeList;
        }
    }

    @Override
    public Long countStoreList(PageRequestDTO pageRequestDTO) {

        PageRequestDTO dto = paymentBookHistoryCustomRepository.bindingMethod(pageRequestDTO);
        String order = dto.getDateOrder();
        String keyword = dto.getKeyword();
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (dto.getStartDate() != null) {
            startDate = LocalDate.parse(dto.getStartDate(), formatter).atStartOfDay();
            endDate = LocalDate.parse(dto.getEndDate(), formatter).atTime(LocalTime.MAX);
        }

        log.info("************StoreRepo count page:{},order:{},keyword:{},startDate:{},endDate:{}",pageRequestDTO.getPage(),order,keyword,startDate,endDate);

        if (startDate == null && keyword == null ) { // 키워드 없음 + 기간 없음
            log.info("**************************키워드 없음 + 기간 없음");
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "order by s.createDate "+ order +" ")
                    .getSingleResult();
            return result;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "where s.createDate between :startDate And :endDate " +
                            "order by s.createDate "+ order +" ")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
            return result;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String str = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "order by s.createDate "+ order +" ")
                    .setParameter("keyword", keyword)
                    .getSingleResult();
            return result;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String str = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "and s.createDate between :startDate And :endDate  " +
                            "order by s.createDate " + order + " ")
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
        String s = "s";
        switch (searchType) {
            case "storeId" :
                s += ".storeId";
                break;
                case "storeOwner" :
                s += ".storeOwner";
                break;
            case "storeTitle" :
                s += ".storeTitle";
                break;
            case "storePhoneNum" :
                s += ".storePhoneNum";
                break;
            case "storeAddress" :
                s += ".storeAddress";
                break;
            case "storeStatus" :
                s += ".storeStatus";
                break;
        }
        return s;
    }

}
