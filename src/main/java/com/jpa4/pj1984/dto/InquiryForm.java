package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InquiryForm {
    private String storeTitle;
    private Long storeId;
    private Long userNo;
    private String inquiryTitle;
    private String inquiryDetail;

    // DTO -> Entity
    public Inquiry toEntity() {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryTitle(inquiryTitle);
        inquiry.setInquiryDetail(inquiryDetail);
        return inquiry;
    }


}
