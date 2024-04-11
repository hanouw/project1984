package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

    private Long userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private MemberStatus userStatus;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;



    // DTO -> Entity
    public Member toEntity(){
        Member member = new Member();
        member.setUserId(userId);
        member.setUserPassword(userPassword);
        member.setUserName(userName);
        member.setUserEmail(userEmail);
        member.setUserPhoneNum(userPhoneNum);
        member.setUserStatus(MemberStatus.USER);
        return member;
    }

}
