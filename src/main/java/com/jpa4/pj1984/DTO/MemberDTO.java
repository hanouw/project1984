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

    private Long user_no;
    private String user_id;
    private String user_password;
    private String user_name;
    private String user_email;
    private String user_phone_num;
    private Role user_role;
    private Status user_status;
    private LocalDateTime createDate;



    // DTO -> Entity
    public Member toEntity(){
        Member member = new Member();
        member.setUser_id(user_id);
        member.setUser_password(user_password);
        member.setUser_name(user_name);
        member.setUser_email(user_email);
        member.setUser_phone_num(user_phone_num);
        member.setUser_role(Role.USER);
        member.setUser_status(Status.USER);
        return member;
    }
}
