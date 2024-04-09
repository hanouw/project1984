package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.PaymentBookDTO;
import com.jpa4.pj1984.domain.PaymentBook;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentBookStatus;
import com.jpa4.pj1984.repository.BookRepository;
import com.jpa4.pj1984.repository.MemberRepository;
import com.jpa4.pj1984.repository.PaymentBookHistoryRepository;
import com.jpa4.pj1984.repository.PaymentBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentBookService {

    private final PaymentBookRepository paymentBookRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PaymentBookHistoryRepository paymentBookHistoryRepository;

    // 책 주문 등록
    public void saveOrder(Long userNo, PaymentBookDTO paymentBookDTO) {

        PaymentBook order = new PaymentBook();
        order.setOrderBookId(paymentBookDTO.getOrderBookId());
        order.setMember(memberRepository.findByUserId("")); // 추후 수정 필요
        order.setOrderBookMethod(paymentBookDTO.getOrderBookMethod());
        order.setPaymentBookStatus(PaymentBookStatus.COMPLETE);
        paymentBookRepository.save(order);

        // 책 주문 내역 등록
        List<PaymentBookHistory> histories = new ArrayList<>();
        for (String bookList : paymentBookDTO.getSelectedBooks()) {
            PaymentBookHistory history = new PaymentBookHistory();
            long bookId = Long.parseLong(bookList);
            history.setPaymentBook(paymentBookRepository.findById(bookId).orElse(null));
            history.setBook(bookRepository.findById(bookId).orElse(null));
            histories.add(history);
            paymentBookHistoryRepository.save(history);
        }


    }
}
