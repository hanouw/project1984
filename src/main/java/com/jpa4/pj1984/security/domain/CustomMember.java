//package com.jpa4.pj1984.security.domain;
//
//import com.jpa4.pj1984.domain.Member;
//import com.jpa4.pj1984.dto.MemberDTO;
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//@Getter
//public class CustomMember extends User {
//
//    private MemberDTO member;
//
//    public CustomMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public CustomMember(Member member){
//        super(member.getUserId(), member.getUserPassword(), Arrays.asList(new SimpleGrantedAuthority(member.getUserStatus().getValue())));
//        this.member = new MemberDTO(member);
//    }
//
//}
