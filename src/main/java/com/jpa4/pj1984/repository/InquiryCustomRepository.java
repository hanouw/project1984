package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Inquiry;
import com.jpa4.pj1984.dto.PageRequestDTO;

import java.util.List;

public interface InquiryCustomRepository {
    List<Inquiry> findInquiryList(PageRequestDTO pageRequestDTO);

    Long countInquiryList(PageRequestDTO pageRequestDTO);

    String searchTypeMethod(PageRequestDTO pageRequestDTO);
}
