package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.BookStatus;
import com.jpa4.pj1984.domain.PaymentBookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 주문 목록이나 주문 상세 정보 화면에 뿌려줄때 사용할 DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long orderBookNo; // auto_increment
    private Long orderBookId; // 2024040300000
    private String userId;
    private String userName;
    private Long isbn;
    private String bookTitle;
    private String storeTitle;
    private PaymentBookStatus paymentBookStatus;
    private String orderBookMethod;
    private LocalDateTime createDate;

    private String bookPub;
    private String bookEbookPrice;
    // private String reviewStatus;
}
