package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Inquiry;
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
public class InquiryCustomRepositoryImpl implements InquiryCustomRepository{

    @PersistenceContext
    private EntityManager em;

    @Qualifier("paymentBookHistoryCustomRepositoryImpl")
    private final PaymentBookHistoryCustomRepository paymentBookHistoryCustomRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<Inquiry> findInquiryList(PageRequestDTO pageRequestDTO) {
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

        log.info("-------------InquiryCustomRepositoryImpl order:{}, keyword:{}, startDate:{}, endDate:{}", order, keyword, startDate, endDate);
        if (startDate == null && keyword == null ) { // 키워드 없음 + 기간 없음
            log.info("**************************키워드 없음 + 기간 없음");
            List<Inquiry> storeList = em.createQuery("select i from Inquiry i " +
                            "order by i.createDate "+ order +" ", Inquiry.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            List<Inquiry> storeList = em.createQuery("select i from Inquiry i " +
                            "where i.createDate between :startDate And :endDate " +
                            "order by i.createDate "+ order +" ", Inquiry.class)
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
            List<Inquiry> storeList = em.createQuery("select i from Inquiry i " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "order by i.createDate "+ order +" ", Inquiry.class)
                    .setParameter("keyword", keyword)
                    .setFirstResult(offset)
                    .setMaxResults(pageRequestDTO.getSize())
                    .getResultList();
            return storeList;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String str = searchTypeMethod(pageRequestDTO);
            List<Inquiry> storeList = em.createQuery("select i from Inquiry i " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "and i.createDate between :startDate And :endDate  " +
                            "order by i.createDate " + order + " ", Inquiry.class)
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
    public Long countInquiryList(PageRequestDTO pageRequestDTO) {
        PageRequestDTO dto = paymentBookHistoryCustomRepository.bindingMethod(pageRequestDTO);
        String order = dto.getDateOrder();
        String keyword = dto.getKeyword();
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (dto.getStartDate() != null) {
            startDate = LocalDate.parse(dto.getStartDate(), formatter).atStartOfDay();
            endDate = LocalDate.parse(dto.getEndDate(), formatter).atTime(LocalTime.MAX);
        }

        log.info("************InquiryCustomRepositoryImpl /count page:{},order:{},keyword:{},startDate:{},endDate:{}",pageRequestDTO.getPage(),order,keyword,startDate,endDate);

        if (startDate == null && keyword == null ) { // 키워드 없음 + 기간 없음
            log.info("**************************키워드 없음 + 기간 없음");
            Long result = (Long) em.createQuery("select count(Inquiry) from Inquiry i " +
                            "order by i.createDate "+ order +" ")
                    .getSingleResult();
            return result;
        }
        else if (keyword == null && startDate != null) { // 키워드 없음 + 기간 있음
            log.info("**************************키워드 없음 + 기간 있음");
            Long result = (Long) em.createQuery("select count(Inquiry) from Inquiry i " +
                            "where i.createDate between :startDate And :endDate " +
                            "order by i.createDate "+ order +" ")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();
            return result;
        }
        else if (startDate == null && keyword != null) { // 키워드 있음 + 기간 없음
            log.info("**************************키워드 있음 + 기간 없음");
            String str = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(Inquiry) from Inquiry i " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "order by i.createDate "+ order +" ")
                    .setParameter("keyword", keyword)
                    .getSingleResult();
            return result;
        }
        else { // 키워드 있음+ 기간 있음
            log.info("**************************키워드 있음 + 기간 있음");
            String str = searchTypeMethod(pageRequestDTO);
            Long result = (Long) em.createQuery("select count(Inquiry) from Inquiry i " +
                            "where " + str + " like concat('%', :keyword, '%') " +
                            "and i.createDate between :startDate And :endDate  " +
                            "order by i.createDate " + order + " ")
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
        String s = "i";
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
            case "storeTitle" :
                s += ".store.storeTitle";
                break;
            case "inquiryTitle" :
                s += ".inquiryTitle";
                break;
            case "inquiryDetail" :
                s += ".inquiryDetail";
                break;
        }
        return s;
    }
}
