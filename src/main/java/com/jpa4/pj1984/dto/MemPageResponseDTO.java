package com.jpa4.pj1984.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class MemPageResponseDTO extends PageResponseDTO {

    protected List<PaymentMemDTO> paymentMemDTO;

    public MemPageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount) {
        super(pageRequestDTO, totalCount);
    }

    public MemPageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount, List<PaymentMemDTO> paymentMemDTO) {
        super(pageRequestDTO, totalCount);
        this.paymentMemDTO = paymentMemDTO;
    }
}
