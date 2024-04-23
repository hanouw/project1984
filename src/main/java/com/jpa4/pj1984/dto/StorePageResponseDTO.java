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
public class StorePageResponseDTO extends PageResponseDTO{
    protected List<StoreDTO> storeList;

    public StorePageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount) {
        super(pageRequestDTO, totalCount);
    }

    public StorePageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount, List<StoreDTO> storeList) {
        super(pageRequestDTO, totalCount);
        this.storeList = storeList;
    }
}
