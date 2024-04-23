package com.jpa4.pj1984.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageResponseDTO {
    protected UserPageRequestDTO userPageRequestDTO; // 페이지 요청 정보 (page, size)
    protected int startPage, endPage; // 화면상 페이지 시작 번호, 페이지 끝 번호
    protected List<Integer> pageNumList; // 화면에 뿌려줄 페이지 번호들
    protected boolean prev, next; // 이전 페이지, 다음페이지 여부
    protected Long totalCount; // 전체 글의 개수
    protected boolean lastPage; // 마지막 페이지인지 여부

}
