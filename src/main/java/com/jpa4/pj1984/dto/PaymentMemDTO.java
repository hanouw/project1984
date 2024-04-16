package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.PaymentMem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMemDTO {
    private Long orderMembershipNo; // auto_increment
    private Long orderMembershipId; // 2024041200000
    private String orderMembershipMethod;
    private String membershipStartDate;
    private String membershipEndDate;

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhoneNum;

    private Long storeId;
    private String storeTitle;
    private String storeImgStored;
    private String storeAddress;

    // Entity -> DTO
    public PaymentMemDTO(PaymentMem paymentMem) {
        this.orderMembershipNo = paymentMem.getOrderMembershipNo();
        this.orderMembershipId = paymentMem.getOrderMembershipId();
        this.orderMembershipMethod = paymentMem.getOrderMembershipMethod();
        this.membershipStartDate = displayTime(paymentMem.getMembershipStartDate());
        this.membershipEndDate = displayTime(paymentMem.getMembershipEndDate());

        this.userId = paymentMem.getMember().getUserId();
        this.userName = paymentMem.getMember().getUserName();
        this.userEmail = paymentMem.getMember().getUserEmail();
        this.userPhoneNum = paymentMem.getMember().getUserPhoneNum();

        this.storeId = paymentMem.getStore().getStoreId();
        this.storeTitle = paymentMem.getStore().getStoreTitle();
        this.storeImgStored = paymentMem.getStore().getStoreImageStored();
        this.storeAddress = paymentMem.getStore().getStoreAddress();
    }

    public String displayTime(LocalDateTime createDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = createDate.format(formatter);
        return formattedDate;
    }
}
