package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Inquiry;
import lombok.Data;

@Data
public class InquiryDTO {
    private Long inquiryId;

    //Entity -> DTO
    public InquiryDTO(Inquiry inquiry){
        this.inquiryId = inquiry.getInquiryId();
    }
}


