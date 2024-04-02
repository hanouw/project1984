package com.jpa4.pj1984.DTO;

import com.jpa4.pj1984.domain.MemberRole;
import com.jpa4.pj1984.domain.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDTO {
    private String userId;
    private String userPassword;
    private MemberRole userMemberRole = MemberRole.USER;
    private MemberStatus userMemberStatus = MemberStatus.USER;
}
