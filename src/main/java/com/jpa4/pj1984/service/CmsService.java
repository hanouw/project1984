package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Membership;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentMem;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CmsService {

    private final PasswordEncoder storePasswordEncoder;
    private final StoreRepository storeRepository;
    private final CmsRepository cmsRepository;
    private final CmsCustomRepositoryImpl cmsCustomRepository;
    private final PaymentBookHistoryRepository paymentBookHistoryRepository;
    private final PaymentMemRepository paymentMemRepository;
    private final MembershipRepository membershipRepository;
    private final EntityManager entityManager;

    // 서점관리 - 서점 정보 등록
    public String save(StoreForm storeForm){
        storeForm.setStorePassword(storePasswordEncoder.encode(storeForm.getStorePassword()));
        Store saved = cmsRepository.save(storeForm.toSignupEntity());
        entityManager.flush();
        Long storeId = saved.getStoreId();
        Membership membership = membershipRepository.findById(1L).orElse(null);
        if (membership == null) {
            Membership newMembership = new Membership(1);
            membershipRepository.save(newMembership);
            entityManager.flush();
            Store store = storeRepository.findById(storeId).orElse(null);
            store.setMembership(newMembership);
            entityManager.flush();
        }
        Store store = storeRepository.findById(storeId).orElse(null);
        store.setMembership(membership);
        entityManager.flush();
        return null;
    }

    // 서점 아이디로 전체 찾아오기
    public StoreDTO findStoreById(String storeLoginId){
        try {
            Store store = cmsCustomRepository.findByStoreLoginId(storeLoginId);
            return(new StoreDTO(store));
        } catch (Exception e){
            return null;
        }
    }

    public List<StoreDTO> findFilterStore(String filterType, String keyword){
        try {
            List<Store> storeList = cmsRepository.findAll();
            List<StoreDTO> storeDTOList = null;
            switch (filterType){
                case "email":
                    storeDTOList = storeList.stream()
                            .filter(b -> b.getStoreEmail().toString().contains(keyword))
                            .map(b -> new StoreDTO())
                            .collect(Collectors.toList());
                    log.info("******* email storeDTOList = {}", storeDTOList);
                    break;
                case "title":
                    storeDTOList = storeList.stream()
                            .filter(b -> b.getStoreTitle().toString().contains(keyword))
                            .map(b -> new StoreDTO())
                            .collect(Collectors.toList());
                    log.info("******* title storeDTOList = {}", storeDTOList);
                    break;
                case "phoneNum":
                    storeDTOList = storeList.stream()
                            .filter(b -> b.getStorePhoneNum().toString().contains(keyword))
                            .map(b -> new StoreDTO())
                            .collect(Collectors.toList());
                    log.info("******* phoneNum storeDTOList = {}", storeDTOList);
                    break;
            }
            return storeDTOList;
        }catch (Exception e){
            return null;
        }
    }

    // 판매자 로그인
//    public StoreLoginForm login(StoreLoginForm storeloginForm){
//        Store store = cmsCustomRepository.findByStoreLoginId(storeloginForm.getStoreLoginId());
//        log.info("******* CmsService = {}", store);
//        if(store != null){ // Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
//            if(storeloginForm.getStorePassword().equals(store.getStorePassword())){
//                return storeloginForm;
//            }
//        }
//        return null;
//    }

    // 주문관리 - 주문 목록 조회 판매자 ver
    public List<PaymentBookHistoryDTO> findHistoryList(PageRequestDTO pageRequestDTO) {
        List<PaymentBookHistory> historyEntityList = paymentBookHistoryRepository.findBookList(pageRequestDTO);
        List<PaymentBookHistoryDTO> list = new ArrayList<>();
        for (PaymentBookHistory orderList : historyEntityList) {
            PaymentBookHistoryDTO paymentBookHistoryDTO = new PaymentBookHistoryDTO(orderList);
            list.add(paymentBookHistoryDTO);
        }
        return list;
    }

    // 주문관리 - 검색된 주문 개수 조회 판매자 ver
    public Long countHistoryList(PageRequestDTO pageRequestDTO) {
        return paymentBookHistoryRepository.countBookList(pageRequestDTO);
    }

    // 주문관리 - 주문 상세페이지 조회
    public PaymentBookHistoryDTO findOneBookHistory(Long orderBookHistoryId) {
        log.info("************CmsService orderBookHistoryId:{}", orderBookHistoryId);
        PaymentBookHistoryDTO dto = new PaymentBookHistoryDTO(paymentBookHistoryRepository.findById(orderBookHistoryId).orElse(null));
        log.info("************CmsService dto:{}", dto);
        return dto;
    }

    // 구독권 가격 조회
    public MembershipDTO findMembershipPrice() {
        Membership membership = membershipRepository.findById(1L).orElse(null);
        if (membership.getPrice() == null) {
            MembershipDTO membershipDTO = new MembershipDTO();
            membershipDTO.setPrice("아직 정해진 가격이 없습니다.");
            return membershipDTO;
        }
        return new MembershipDTO(membership);
    }

    // 구독권 수정
    public void modifyMembershipPrice(MembershipDTO membershipDTO) {
        Membership membership = membershipRepository.findById(1L).orElse(null);
        if (membership == null) {
            membershipRepository.save(membershipDTO.toEntity());
        } else {
            membership.setPrice(membershipDTO.getPrice());
        }
    }

    // 구독관리 - 구독내역 목록 조회
    public List<PaymentMemDTO> findMembershipList(PageRequestDTO pageRequestDTO) {
        List<PaymentMem> membershipEntityList = paymentMemRepository.findMembershipList(pageRequestDTO);
        List<PaymentMemDTO> list = new ArrayList<>();
        for (PaymentMem membershipList : membershipEntityList) {
            PaymentMemDTO paymentMemDTO = new PaymentMemDTO(membershipList);
            list.add(paymentMemDTO);
        }
        return list;
    }

    // 구독관리 - 검색된 구독내역 개수 조회
    public Long countMembershipList(PageRequestDTO pageRequestDTO) {
        return paymentMemRepository.countMembershipList(pageRequestDTO);
    }

    // 구독관리 - 구독내역 상세페이지 조회
    public PaymentMemDTO findOneMemHistory(Long orderMembershipNo) {
        log.info("************CmsService findOneMemHistory orderMembershipNo:{}", orderMembershipNo);
        PaymentMemDTO dto = new PaymentMemDTO(paymentMemRepository.findById(orderMembershipNo).orElse(null));
        log.info("************CmsService findOneMemHistory dto:{}", dto);
        return dto;
    }

}
