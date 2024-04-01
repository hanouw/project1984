package com.jpa4.pj1984.service;
import com.jpa4.pj1984.DTO.StoreDTO;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store save(StoreDTO storeDTO){
        Store StoreSaved = storeRepository.save(storeDTO.toEntity());
        return null;
    }
}
