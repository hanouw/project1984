package com.jpa4.pj1984.service;

import com.jpa4.pj1984.DTO.PaymentBookHistoryDTO;
import com.jpa4.pj1984.DTO.PaymentDTO;
import com.jpa4.pj1984.domain.Payment;
import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentBookStatus;
import com.jpa4.pj1984.repository.MemberRepository;
import com.jpa4.pj1984.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    public void saveOrder(Long userNo, PaymentDTO paymentDTO) {

        PaymentBookHistory orders = new PaymentBookHistory();
        // orders.setPayment();

        Payment order = new Payment();
        order.setOrderBookId(paymentDTO.getOrderBookId());
        order.setMember(memberRepository.findByUserId("")); // TODO 추후 수정 필요
        order.setOrderBookMethod(paymentDTO.getOrderBookMethod());
        order.setPaymentBookStatus(PaymentBookStatus.COMPLETE);
//        order.setOrderBookHistories();

        // paymentDTO : orderBookId, userNo, orderBookMethod, paymentBookStatus, createDate, selectedBooks
//        PaymentBookHistoryDTO orders = new PaymentBookHistoryDTO();
//        for(int i = 0; i < paymentDTO.getSelectedBooks().size(); i++ ){
//            orders.setIsbn(Long.parseLong(paymentDTO.getSelectedBooks().get(i)));
//        }

    }
}
