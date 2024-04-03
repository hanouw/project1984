package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.dto.StoreLoginForm;
import com.jpa4.pj1984.repository.CmsCustomRepository;
import com.jpa4.pj1984.repository.CmsCustomRepositoryImpl;
import com.jpa4.pj1984.repository.CmsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CmsService {

    private final CmsRepository cmsRepository;
    private final CmsCustomRepositoryImpl cmsCustomRepository;

    public String save(StoreForm storeForm){
        cmsRepository.save(storeForm.toSignupEntity());
        return null;
    }

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
}
