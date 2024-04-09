package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.StoreReview;
import com.jpa4.pj1984.dto.StoreReviewDTO;
import com.jpa4.pj1984.repository.StoreReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreReviewService {

    private final StoreReviewRepository storeReviewRepository;

    // 서점리뷰 목록 조회
    public List<StoreReviewDTO> findAll() {
        List<StoreReview> all = storeReviewRepository.findAll();
        System.out.println("all = " + all);//all까지는 불러옴
        List<StoreReviewDTO> list = all.stream()
                .map(b -> new StoreReviewDTO(b))
                .collect(Collectors.toList());
        System.out.println("list = " + list);
        return list;
    }

    // 댓글 상세 조회 (한개 조회)
    public StoreReview getOneStoreReview(Long storeReviewId) {

        StoreReview storeReview = storeReviewRepository.findById(storeReviewId).orElse(null);

        return storeReview;
    }
}
