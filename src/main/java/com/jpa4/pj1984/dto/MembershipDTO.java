package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Membership;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {
    private String price;
    private String createDate;
    private String lastModifiedDate;

    public MembershipDTO(Membership membership) {
        this.price = membership.getPrice();
        this.createDate = displayTime(membership.getCreateDate());
        this.lastModifiedDate = displayTime(membership.getLastModifiedDate());
    }

    public Membership toEntity() {
        Membership membership = new Membership();
        membership.setPrice(price);
        return membership;
    }

    public String displayTime(LocalDateTime createDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = createDate.format(formatter);
        return formattedDate;
    }
}
