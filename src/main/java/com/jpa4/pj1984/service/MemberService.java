package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.MemberDTO;
import com.jpa4.pj1984.dto.MemberForm;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder PasswordEncoder;

    // 회원 등록
    public Member save(MemberForm memberForm){
        memberForm.setUserPassword(PasswordEncoder.encode(memberForm.getUserPassword()));
        Member memberSaved = memberRepository.save(memberForm.toEntity());
        return memberSaved;
    }

    // 회원 목록 조회하기
    public List<MemberDTO> findAllMember(){
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> memberFromList = new ArrayList<>();
        for (Member val : memberList) {
            memberFromList.add(new MemberDTO(val));
        }
        return memberFromList;
    }

    // No로 회원 하나 데이터 찾기
    public MemberDTO findMemberById(Long userNo){
        Optional<Member> member = memberRepository.findById(userNo);
        log.info("******* MemberService / findMemberById = {}", member.orElse(null).getUserStatus().getValue());
        return member.map(MemberDTO::new).orElse(null);
//        위 방법이 더 쉽고 깔끔, 그걸 풀면 아래코드와 동일함
//        if(member.isPresent()){
//            return new MemberDTO(member.get());
//        }
//        return null;
    }

    // 회원 정보 수정
    public void modifyMember(MemberDTO memberDTO){
        Member member = memberRepository.findById(memberDTO.getUserNo()).orElse(null);
        member.setUserId(memberDTO.getUserId());
        member.setUserName(memberDTO.getUserName());
        member.setUserPhoneNum(memberDTO.getUserPhoneNum());
        member.setUserEmail(memberDTO.getUserEmail());
        member.setUserStatus(memberDTO.getUserStatus());

//        log.info("******* memberDTO = {}", memberDTO.getUserId());
//        Member member = memberRepository.findById(memberDTO.getUserNo())
//                .map(m -> {
//                    m.setUserId(memberDTO.getUserId());
//                    m.setUserName(memberDTO.getUserName()); // Likely meant userName here
//                    m.setUserPhoneNum(memberDTO.getUserPhoneNum());
//                    m.setUserEmail(memberDTO.getUserEmail());
//                    m.setUserStatus(memberDTO.getUserStatus());
//                    return m;
//                })
//                .orElse(null);
//        log.info("******* member = {}", member.getUserId());
    }

    // 로그인
//    public MemberLoginDTO login(MemberLoginDTO memberLoginDTO){
//        Member dbMember = memberRepository.findByUserId(memberLoginDTO.getUserId());
////        if(dbMember.isPresent()) { // Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
//        if(dbMember != null){
//            MemberStatus memberStatus = dbMember.getUserStatus();
//            if(dbMember.getUserPassword().equals(memberLoginDTO.getUserPassword()) && memberStatus == MemberStatus.USER){
//                memberLoginDTO.setUserStatus(memberStatus);
//                return memberLoginDTO;
//            }
//        }
//        return null;
//    }
}
