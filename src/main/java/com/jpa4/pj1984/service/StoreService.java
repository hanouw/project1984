package com.jpa4.pj1984.service;
import com.jpa4.pj1984.dto.StoreForm;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Store;
import com.jpa4.pj1984.repository.MemberRepository;
import com.jpa4.pj1984.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public Store save(StoreForm storeForm, String userId){
        Member member = memberRepository.findByUserId(userId);
        Store entity = storeForm.toEntity();
        entity.setMember(member);
        Store storeSaved = storeRepository.save(entity);
        return storeSaved;
    }
}
