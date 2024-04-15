package com.jpa4.pj1984.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO { // 구 Pager
    private int page; // 요청하는 페이지번호
    private int size = 10; // 한페이지에 보여줄 글 개수
    private String keyword; // 검색 키워드
    private String searchType; // 검색 유형 (t, w, c, tc, tw, twc)
    private String dateOrder; // 최신순, 이전순
    private String datePeriod; // 검색기간
    private String startDate;
    private String endDate;
}
