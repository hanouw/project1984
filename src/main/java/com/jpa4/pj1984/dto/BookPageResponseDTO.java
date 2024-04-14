package com.jpa4.pj1984.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPageResponseDTO extends PageResponseDTO{

    protected List<PaymentBookHistoryDTO> orderList;

    public BookPageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount) {
        super(pageRequestDTO, totalCount);
    }

    public BookPageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount, List<PaymentBookHistoryDTO> orderList) {
        super(pageRequestDTO, totalCount);
        this.orderList = orderList;
    }
}
