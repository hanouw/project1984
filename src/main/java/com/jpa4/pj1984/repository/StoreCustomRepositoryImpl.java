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


    @Override
    public PageRequestDTO bindingMethod(PageRequestDTO pageRequestDTO) {

        PageRequestDTO newDTO = new PageRequestDTO();

        log.info("************bindingMethod pageRequestDTO:{}", pageRequestDTO);

        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            newDTO.setDateOrder("desc");
            pageRequestDTO.setDateOrder("desc");
        } else if (pageRequestDTO.getDateOrder() != null || !pageRequestDTO.getDateOrder().equals("")) {
            newDTO.setDateOrder(pageRequestDTO.getDateOrder());
        }
        if (pageRequestDTO.getKeyword() == null || pageRequestDTO.getKeyword().equals("")) {
            newDTO.setKeyword(null);
        } else {
            newDTO.setKeyword(pageRequestDTO.getKeyword());
        }
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (pageRequestDTO.getDatePeriod() == null || pageRequestDTO.getDatePeriod().equals("")) {
            newDTO.setStartDate(null);
            newDTO.setEndDate(null);
        } else {
            if (pageRequestDTO.getDatePeriod().equals("today")) {
                startDate = LocalDate.now().atStartOfDay();
                endDate = LocalDate.now().atTime(LocalTime.MAX);
                newDTO.setStartDate(startDate.format(formatter));
                newDTO.setEndDate(endDate.format(formatter));
                pageRequestDTO.setStartDate(startDate.format(formatter));
                pageRequestDTO.setEndDate(endDate.format(formatter));
            } else if (pageRequestDTO.getDatePeriod().equals("oneWeek")) {
                startDate = LocalDate.now().minusDays(7).atStartOfDay();
                endDate = LocalDateTime.now();
                pageRequestDTO.setStartDate(startDate.format(formatter));
                pageRequestDTO.setEndDate(endDate.format(formatter));
                newDTO.setStartDate(startDate.format(formatter));
                newDTO.setEndDate(endDate.format(formatter));
            } else if (pageRequestDTO.getDatePeriod().equals("oneMonth")) {
                startDate = LocalDate.now().minusDays(30).atStartOfDay();
                endDate = LocalDateTime.now();
                pageRequestDTO.setStartDate(startDate.format(formatter));
                pageRequestDTO.setEndDate(endDate.format(formatter));
                newDTO.setStartDate(startDate.format(formatter));
                newDTO.setEndDate(endDate.format(formatter));
            }
        }
        if (pageRequestDTO.getStartDate() == null || pageRequestDTO.getStartDate().equals("")) {
            newDTO.setStartDate(null);
            newDTO.setEndDate(null);
        }
        if (pageRequestDTO.getStartDate() != null && !pageRequestDTO.getStartDate().equals("")) {
            startDate = LocalDate.parse(pageRequestDTO.getStartDate(), formatter).atStartOfDay();
            endDate = LocalDate.parse(pageRequestDTO.getEndDate(), formatter).atTime(LocalTime.MAX);
            newDTO.setStartDate(startDate.format(formatter));
            newDTO.setEndDate(endDate.format(formatter));
        }
        return newDTO;
    }

    @Override
    public List<Store> findStoreListByStoreId(Long storeId, PageRequestDTO pageRequestDTO) {
        int offset = (pageRequestDTO.getPage() - 1) * pageRequestDTO.getSize();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PageRequestDTO dto = bindingMethod(pageRequestDTO);
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
                            "where s.storeId = :storeId " +
                            "order by s.createDate "+ order +" ", Store.class)
                    .setParameter("storeId", storeId)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "where s.storeId = :storeId and s.createDate between :startDate And :endDate " +
                            "order by s.createDate "+ order +" ", Store.class)
                    .setParameter("storeId", storeId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String s = searchTypeMethod(pageRequestDTO);
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "where s.storeId = :storeId and " + s + " like concat('%', :keyword, '%') " +
                            "order by s.createDate "+ order +" ", Store.class)
                    .setParameter("storeId", storeId)
                    .setParameter("keyword", keyword)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String s = searchTypeMethod(pageRequestDTO);
            List<Store> storeList = em.createQuery("select s from Store s " +
                            "where s.storeId = :storeId and " + s + " like concat('%', :keyword, '%') " +
                            "and s.createDate between :startDate And :endDate  " +
                            "order by s.createDate " + order + " ", Store.class)
                    .setParameter("storeId", storeId)
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
    public Long countStoreListByStoreId(Long storeId, PageRequestDTO pageRequestDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PageRequestDTO dto = bindingMethod(pageRequestDTO);
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
                            "where s.storeId = :storeId " +
                            "order by s.createDate "+ order +" ")
                    .setParameter("storeId", storeId)
                    .getSingleResult();
            return result;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "where s.storeId = :storeId and s.createDate between :startDate And :endDate " +
                            "order by s.createDate "+ order +" ")
                    .setParameter("storeId", storeId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
            return result;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String s = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "where s.storeId = :storeId and " + s + " like concat('%', :keyword, '%') " +
                            "order by s.createDate "+ order +" ")
                    .setParameter("storeId", storeId)
                    .setParameter("keyword", keyword)
                    .getSingleResult();
            return result;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String se = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(s) from Store s " +
                            "where s.storeId = :storeId and " + se + " like concat('%', :keyword, '%') " +
                            "and s.createDate between :startDate And :endDate  " +
                            "order by s.createDate " + order + " ")
                    .setParameter("storeId", storeId)
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
        String se = "s";
        switch (searchType) {
            case "storeId" :
                se += ".store.storeId";
                break;
                case "storeOwner" :
                se += ".store.storeOwner";
                break;
            case "storeTitle" :
                se += ".store.storeTitle";
                break;

        }
        return se;
    }

}
