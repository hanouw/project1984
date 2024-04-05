//package com.jpa4.pj1984.security;
//
//import com.jpa4.pj1984.domain.Member;
//import com.jpa4.pj1984.domain.Store;
//import com.jpa4.pj1984.repository.CmsCustomRepositoryImpl;
//import com.jpa4.pj1984.repository.MemberCustomRepositoryImpl;
//import com.jpa4.pj1984.security.domain.CustomCms;
//import com.jpa4.pj1984.security.domain.CustomMember;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class CustomMemberSecurityService implements UserDetailsService {
//
//    private final MemberCustomRepositoryImpl memberCustomRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("******* CustomMemberSecurityService UserDetail");
//        Member member = memberCustomRepository.findByUserId(username); //.orElseThrow(()-> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));
//        if(member == null){
//            throw new UsernameNotFoundException("해당 관리자가 없습니다.");
//        }
//        CustomMember customMember = new CustomMember(member);
//        log.info("******* customCms Authorities = {}",customMember.getAuthorities());
//        return customMember;
//    }
//
//}
