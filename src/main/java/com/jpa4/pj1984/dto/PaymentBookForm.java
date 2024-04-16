package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.PaymentBookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// 장바구니에서 결제하기 버튼 누를때 넘어오는 정보 받는 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBookForm {
    private Long orderBookId;
    private Long userNo;
    private String orderBookMethod;
    private PaymentBookStatus paymentBookStatus;
    private LocalDateTime createDate;
    private List<String> selectedBooks;




//    // DTO -> Entity
//    public Payment toEntity() {
//        Payment payment = new Payment();
//        payment.setOrderBookId(orderBookId);
//        payment.setOrderBookMethod(orderBookMethod);
//        return payment;
//    }
}
