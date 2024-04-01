package com.jpa4.repository;

import com.jpa4.pj1984.DTO.MemberDTO;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Role;
import com.jpa4.pj1984.domain.Status;
import com.jpa4.pj1984.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testSignUp() {
        // Given
//        MemberDTO member = new MemberDTO();
//        member.setUser_id("testUser");
//        member.setUser_password("testPassword");
//        member.setUser_name("Test User");
//        member.setUser_email("test@example.com");
//        member.setUser_phone_num("1234567890");
//        member.setUser_role(Role.USER);
//        member.setUser_status(Status.USER);
//
//        // When
//        Member savedMember = memberService.save(member);
//
//        // Then
//        assertNotNull(savedMember.getUser_no());
//        assertEquals(member.getUser_id(), savedMember.getUser_id());
//        assertEquals(member.getUser_name(), savedMember.getUser_name());
//        assertEquals(member.getUser_email(), savedMember.getUser_email());
//        assertEquals(member.getUser_phone_num(), savedMember.getUser_phone_num());
//        assertEquals(member.getUser_role(), savedMember.getUser_role());
//        assertEquals(member.getUser_status(), savedMember.getUser_status());
    }
}
