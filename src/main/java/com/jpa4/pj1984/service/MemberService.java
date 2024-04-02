package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.MemberDTO;
import com.jpa4.pj1984.dto.MemberLoginDTO;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.MemberStatus;
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

    public MemberLoginDTO login(MemberLoginDTO memberLoginDTO){
        Member dbMember = memberRepository.findByUserId(memberLoginDTO.getUserId());
//        if(dbMember.isPresent()) { // Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
        if(dbMember != null){
            MemberStatus memberStatus = dbMember.getUserMemberStatus();
            if(dbMember.getUserPassword().equals(memberLoginDTO.getUserPassword()) && memberStatus == MemberStatus.USER){
                memberLoginDTO.setUserMemberStatus(memberStatus);
                return memberLoginDTO;
            }
        }
        return null;
    }
}
