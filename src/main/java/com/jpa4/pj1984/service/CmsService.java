package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Payment;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.PageRequestDTO;
import com.jpa4.pj1984.dto.PaymentBookHistoryDTO;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.dto.StoreLoginForm;
import com.jpa4.pj1984.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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
    private final PaymentCustomRepository paymentCustomRepository;
    private final PaymentBookHistoryCustomRepositoryImpl paymentBookHistoryCustomRepository;

    // 서점관리 - 서점 정보 등록
    public String save(StoreForm storeForm){
        cmsRepository.save(storeForm.toSignupEntity());
        return null;
    }

    // 판매자 로그인
    public StoreLoginForm login(StoreLoginForm storeloginForm){
        Store store = cmsCustomRepository.findByStoreLoginId(storeloginForm.getStoreLoginId());
        log.info("******* CmsService = {}", store);
        if(store != null){ // Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
            if(storeloginForm.getStorePassword().equals(store.getStorePassword())){
                return storeloginForm;
            }
        }
        return null;
    }

    // 주문 목록 조회 판매자 ver
    public List<PaymentBookHistoryDTO> findHistoryList(Long storeId, PageRequestDTO pageRequestDTO) {
        List<PaymentBookHistory> historyEntityList = paymentBookHistoryCustomRepository.findListByStoreId(storeId, pageRequestDTO);
        List<PaymentBookHistoryDTO> list = new ArrayList<>();
        for (PaymentBookHistory p : historyEntityList) {
            list.add(new PaymentBookHistoryDTO(p));
        }
        return list;
    }

}
