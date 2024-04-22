package com.jpa4.pj1984.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InquiryPageResponseDTO extends PageResponseDTO{
    protected List<InquiryDTO> inquiryList;

    public InquiryPageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount) {
        super(pageRequestDTO, totalCount);
    }

    public InquiryPageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount, List<InquiryDTO> inquiryList) {
        super(pageRequestDTO, totalCount);
        this.inquiryList = inquiryList;
    }
}
