package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.MemberDTO;
import com.jpa4.pj1984.dto.MemberForm;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.dto.UserPageRequestDTO;
import com.jpa4.pj1984.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    // 회원 목록 조회하기(Page) -> 전체
    public Page<MemberDTO> findAllMember(Pageable pageable){
        Page<Member> all = memberRepository.findAll(pageable);
        Page<MemberDTO> list = all.map(b -> new MemberDTO(b));
        return list;
    }
    // 회원 목록 조회하기(Page) -> 전체
    public Page<MemberDTO> findByUserNameContaining(String keyword, Pageable pageable){
        Page<Member> all = memberRepository.findByUserNameContaining(keyword, pageable);
        Page<MemberDTO> list = all.map(b -> new MemberDTO(b));
        return list;
    }
    // 회원 목록 조회하기(Page) -> 전체
    public Page<MemberDTO> findByUserIdContaining(String keyword, Pageable pageable){
        Page<Member> all = memberRepository.findByUserIdContaining(keyword, pageable);
        Page<MemberDTO> list = all.map(b -> new MemberDTO(b));
        return list;
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

    // 회원 필터링해서 가져오기
    public List<MemberDTO> searchMember(UserPageRequestDTO userPageRequestDTO) {
        int page = userPageRequestDTO.getPage();
        List<Member> allMember = memberRepository.findAll();
        List<MemberDTO> list = null;
        String keyword = userPageRequestDTO.getKeyword();
        try{
            switch (userPageRequestDTO.getSearchType()){
                case "userNo" :
                    list = allMember.stream()
                            .filter(b -> b.getUserNo().toString().contains(keyword))
                            .map(b -> new MemberDTO())
    //                        .limit(2)
                            .collect(Collectors.toList());
                    break;
                case "userId" :
                    list = allMember.stream()
                            .filter(b -> b.getUserId().contains(keyword))
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                case "userName":
                    list = allMember.stream()
                            .filter(b -> b.getUserName().contains(keyword))
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                case "userPhoneNum":
                    list = allMember.stream()
                            .filter(b -> b.getUserPhoneNum().contains(keyword))
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                case "userEmail":
                    list = allMember.stream()
                            .filter(b -> b.getUserEmail().contains(keyword))
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                case "userOrder":
                    try{
                        int intKeyword =  Integer.parseInt(keyword);
                        list = allMember.stream() // todo 회원에 주문 수를 추가해야할듯함...
    //                            .filter(b -> (b.getUserOrder > intKeyword))
                                .map(b -> new MemberDTO())
                                .collect(Collectors.toList());
                    }catch (Exception e){
                        log.info("it's not int");
                    }
                    break;
                case "userSubscribe":
                    list = allMember.stream()
    //                        .filter(b -> b.getUserSubscribe().contains(keyword)) // todo 회원에 구독수도 추가해야할듯함...
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                case "userStatus":
                    list = allMember.stream()
                            .filter(b -> b.getUserStatus().getValue().equals(keyword))
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                case "createDate":
                    list = allMember.stream()
                            .filter(b -> b.getCreateDate().toString().contains(keyword))
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
                default:
                    list = allMember.stream()
                            .map(b -> new MemberDTO())
                            .collect(Collectors.toList());
                    break;
            }
        }catch (Exception e){
            list = allMember.stream()
                    .map(MemberDTO::new)
                    .collect(Collectors.toList());;
        }
        return list;
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
