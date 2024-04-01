package com.jpa4.pj1984.service;

import com.jpa4.pj1984.DTO.MemberDTO;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberDTO memberDTO){
        Member memberSaved = memberRepository.save(memberDTO.toEntity());
        return memberSaved;
    }

    public MemberDTO login(MemberDTO memberDTO){
        Optional<Member> dbMember = memberRepository.findById(memberDTO.getUserNo());
        if(dbMember.isPresent()) { // Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
            if(dbMember.get().getUserPassword().equals(memberDTO.getUserPassword())){
                return memberDTO;
            }
        }
//        Member member = memberRepository.findById(memberDTO.getUser_no()).orElse(null);
//        if(member.getUser_password().equals(memberDTO.getUser_password())){
//            return memberDTO;
//        }
        return null;
    }
}
