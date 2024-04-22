package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.*;
import com.jpa4.pj1984.dto.AnswerForm;
import com.jpa4.pj1984.dto.BookForm;
import com.jpa4.pj1984.dto.InquiryDTO;
import com.jpa4.pj1984.dto.InquiryForm;
import com.jpa4.pj1984.repository.AnswerRepository;
import com.jpa4.pj1984.repository.InquiryRepository;
import com.jpa4.pj1984.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InquiryService{

    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;
    private final StoreRepository storeRepository;

    // 문의리뷰 목록 조회
    public List<InquiryDTO> findAll() {
        List<Inquiry> all = inquiryRepository.findAll();
        System.out.println("all = " + all);//all까지는 불러옴
        List<InquiryDTO> list = all.stream()
                .map(b -> new InquiryDTO(b))
                .collect(Collectors.toList());
        System.out.println("list = " + list);
        return list;
    }

    // 댓글 상세 조회 (한개 조회)
    public Inquiry getOneInquiry(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElse(null);
        return inquiry;
    }

    // 답변 등록
    public void save(Long inquiryId, Long storeId, AnswerForm answerForm){
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElse(null);
        Store store = storeRepository.findById(storeId).orElse(null);
        Answer answer = answerForm.toEntity();
        answer.setInquiry(inquiry);
        answer.setStore(store);
        Answer saved = answerRepository.save(answer);
    }

    // 답변 수정
    public void updateOneAnswer(Long inquiryId, Answer answer){
        log.info("******* InquiryService - answer : {}", answer.getAnswerDetail());
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElse(null); // 문의찾고
        Answer findanswer = inquiry.getAnswer(); // 문의의 답변
        findanswer.setAnswerTitle(answer.getAnswerTitle());
        findanswer.setAnswerDetail(answer.getAnswerDetail());
    }
}
