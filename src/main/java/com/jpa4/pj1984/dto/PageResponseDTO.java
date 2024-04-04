package com.jpa4.pj1984.dto;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 화면에 글 목록 응답할때 함께 보내주는 페이징 처리 정보 보낼 용도
// 직접 모두 계산하는 버전
@Getter
public class PageResponseDTO { // 구 PageDTO
    private PageRequestDTO pageRequestDTO; // 페이지 요청 정보 (page, size)
    private Long totalCount; // 전체 글의 개수
    private int startPage, endPage; // 화면상 페이지 시작 번호, 페이지 끝 번호
    private List<Integer> pageNumList; // 화면에 뿌려줄 페이지 번호들
    private boolean prev, next; // 이전 페이지, 다음페이지 여부
    private int totalPage;
    private boolean lastPage; // 마지막 페이지인지 여부

    public PageResponseDTO(PageRequestDTO pageRequestDTO, Long totalCount) {
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = totalCount;

        this.endPage = (int) Math.ceil((double) pageRequestDTO.getPage() / pageRequestDTO.getSize()) * pageRequestDTO.getSize();
        this.startPage = endPage - (pageRequestDTO.getSize() - 1);

        this.totalPage = (int) Math.ceil((double) this.totalCount / pageRequestDTO.getSize());
        if (this.totalPage < this.endPage) {
            this.endPage = this.totalPage;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < totalPage;

        this.pageNumList = IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());

        this.lastPage = pageRequestDTO.getPage() == totalPage;
    }
}
