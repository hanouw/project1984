package com.jpa4.pj1984.DTO;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Role;
import com.jpa4.pj1984.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private Role userRole;
    private Status userStatus;
    private LocalDateTime createDate;


    // DTO -> Entity
    public Member toEntity(){
        Member member = new Member();
        member.setUserId(userId);
        member.setUserPassword(userPassword);
        member.setUserName(userName);
        member.setUserEmail(userEmail);
        member.setUserPhoneNum(userPhoneNum);
        member.setUserRole(Role.USER);
        member.setUserStatus(Status.USER);
        return member;
    }
}
