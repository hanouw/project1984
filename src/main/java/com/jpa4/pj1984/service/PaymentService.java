package com.jpa4.pj1984.service;

import com.jpa4.pj1984.dto.PaymentDTO;
import com.jpa4.pj1984.domain.Payment;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentBookStatus;
import com.jpa4.pj1984.repository.BookRepository;
import com.jpa4.pj1984.repository.BookRepository;
import com.jpa4.pj1984.repository.MemberRepository;
import com.jpa4.pj1984.repository.PaymentBookHistoryRepository;
import com.jpa4.pj1984.repository.PaymentRepository;
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
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PaymentBookHistoryRepository paymentBookHistoryRepository;

    public void saveOrder(Long userNo, PaymentDTO paymentDTO) {

        Payment order = new Payment();
        order.setOrderBookId(paymentDTO.getOrderBookId());
        order.setMember(memberRepository.findByUserId("")); // TODO 추후 수정 필요
        order.setOrderBookMethod(paymentDTO.getOrderBookMethod());
        order.setPaymentBookStatus(PaymentBookStatus.COMPLETE);
        paymentRepository.save(order);

        List<PaymentBookHistory> histories = new ArrayList<>();
        for (String bookList : paymentDTO.getSelectedBooks()) {
            PaymentBookHistory history = new PaymentBookHistory();
            long isbn = Long.parseLong(bookList);
            history.setPayment(paymentRepository.findById(isbn).orElse(null));
            history.setBook(bookRepository.findById(isbn).orElse(null));
            histories.add(history);
            paymentBookHistoryRepository.save(history);
        }


    }
}
