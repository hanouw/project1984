package com.jpa4.pj1984.service;

import com.jpa4.pj1984.DTO.MemberDTO;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberDTO memberDTO){
        Member memberSaved = memberRepository.save(memberDTO.toEntity());
        return memberSaved;
    }
}
