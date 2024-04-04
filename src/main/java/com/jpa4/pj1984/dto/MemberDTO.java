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
public class MemberDTO {

    private Long userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private MemberStatus userStatus;
    private LocalDateTime createDate;


    // Entity -> DTO
    public MemberDTO(Member member){
        this.userNo = getUserNo();
        this.userPassword = getUserPassword();
        this.userName = getUserName();
        this.userEmail = getUserEmail();
        this.userPhoneNum = getUserPhoneNum();
        this.userStatus = getUserStatus();
    }
}