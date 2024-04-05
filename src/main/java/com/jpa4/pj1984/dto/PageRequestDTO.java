package com.jpa4.pj1984.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    private int page = 1; // 요청하는 페이지번호
    private int size = 10; // 한페이지에 보여줄 글 개수
}
