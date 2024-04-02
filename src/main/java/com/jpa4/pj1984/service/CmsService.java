package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.repository.CmsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CmsService {

    private final CmsRepository cmsRepository;

    public String save(StoreForm storeForm){
        cmsRepository.save(storeForm.toSignupEntity());
        return null;
    }
    public String login(StoreForm storeForm){
        return null;
    }
}
