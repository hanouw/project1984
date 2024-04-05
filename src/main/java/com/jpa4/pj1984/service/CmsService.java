package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Payment;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.*;
import com.jpa4.pj1984.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CmsService {

    private final CmsRepository cmsRepository;
    private final CmsCustomRepositoryImpl cmsCustomRepository;
    private final PaymentBookHistoryRepository paymentBookHistoryRepository;
    private final PasswordEncoder storePasswordEncoder;
    private final PaymentCustomRepository paymentCustomRepository;
    private final PaymentBookHistoryCustomRepositoryImpl paymentBookHistoryCustomRepository;

    // 서점관리 - 서점 정보 등록
    public String save(StoreForm storeForm){
        storeForm.setStorePassword(storePasswordEncoder.encode(storeForm.getStorePassword()));
        cmsRepository.save(storeForm.toSignupEntity());
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
    public List<PaymentResponseDTO> findHistoryList(Long storeId, PageRequestDTO pageRequestDTO) {
        List<PaymentBookHistory> historyEntityList = paymentBookHistoryCustomRepository.findListByStoreId(storeId, pageRequestDTO);
//        List<PaymentBookHistoryDTO> list = new ArrayList<>();
//        for (PaymentBookHistory p : historyEntityList) {
//            list.add(new PaymentBookHistoryDTO(p));
//        }
        List<PaymentResponseDTO> list = new ArrayList<>();
        for (PaymentBookHistory orderList : historyEntityList) {
            PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
            paymentResponseDTO.setOrderBookNo(orderList.getPayment().getOrderBookNo());
            paymentResponseDTO.setOrderBookId(orderList.getPayment().getOrderBookId());
            paymentResponseDTO.setUserId(orderList.getPayment().getMember().getUserId());
            paymentResponseDTO.setUserName(orderList.getPayment().getMember().getUserName());
            paymentResponseDTO.setIsbn(orderList.getBook().getIsbn());
            paymentResponseDTO.setBookTitle(orderList.getBook().getBookTitle());
            //paymentResponseDTO.setStoreTitle(orderList.getBook().getS);
            paymentResponseDTO.setPaymentBookStatus(orderList.getPayment().getPaymentBookStatus());
            paymentResponseDTO.setOrderBookMethod(orderList.getPayment().getOrderBookMethod());
            paymentResponseDTO.setCreateDate(orderList.getPayment().getCreateDate());
            paymentResponseDTO.setBookPub(orderList.getBook().getBookPub());
            paymentResponseDTO.setBookEbookPrice(orderList.getBook().getBookEbookPrice());
            list.add(paymentResponseDTO);
        }
        return list;
    }

}
