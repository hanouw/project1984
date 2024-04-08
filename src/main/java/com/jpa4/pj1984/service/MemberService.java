package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.MemberDTO;
import com.jpa4.pj1984.dto.MemberForm;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        log.info("******* memberList size = {}", memberList.size());
        log.info("******* memberList = {}", memberList.get(0).getUserNo());
        log.info("******* memberList = {}", memberList.get(0).getUserName());
        log.info("******* memberList = {}", memberList.get(0).getUserEmail());
        log.info("******* memberList = {}", memberList.get(0).getUserStatus());
        log.info("******* memberList = {}", memberList.get(0).getUserPhoneNum());
        log.info("******* memberList = {}", memberList.get(0).getCreateDate());
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member val : memberList) {
            log.info("******* val = {}", val.toString());
            memberDTOList.add(new MemberDTO(val));
        }
        log.info("******* 안에 값 개수 = {}", memberDTOList.size());
        log.info("******* memberList = {}", memberDTOList.get(0).getUserNo());
        log.info("******* memberList = {}", memberDTOList.get(0).getUserName());
        log.info("******* memberList = {}", memberDTOList.get(0).getUserEmail());
        log.info("******* memberList = {}", memberDTOList.get(0).getUserStatus());
        log.info("******* memberList = {}", memberDTOList.get(0).getCreateDate());
        return memberDTOList;
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
