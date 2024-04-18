package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Answer;
import com.jpa4.pj1984.domain.Inquiry;
import com.jpa4.pj1984.domain.Member;
import com.jpa4.pj1984.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO { // 가져오는

    private Long inquiryId;
    private Member member;
    private String inquiryTitle;
    private String inquiryDetail;
    private Store store;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Answer answer;


    // Entity -> DTO
    public InquiryDTO(Inquiry inquiry){
        this.inquiryId = inquiry.getInquiryId();
        this.member = inquiry.getMember();
        this.inquiryTitle = inquiry.getInquiryTitle();
        this.inquiryDetail = inquiry.getInquiryDetail();
        this.store = inquiry.getStore();
        this.createDate = inquiry.getCreateDate();
        this.lastModifiedDate = inquiry.getLastModifiedDate();
        this.answer = inquiry.getAnswer();

    }
}


