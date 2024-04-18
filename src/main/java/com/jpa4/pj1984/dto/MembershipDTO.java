package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Membership;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {
    private String price;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    public MembershipDTO(Membership membership) {
        this.price = membership.getPrice();
    }

    public Membership toEntity() {
        Membership membership = new Membership();
        membership.setPrice(price);
        return membership;
    }
}
