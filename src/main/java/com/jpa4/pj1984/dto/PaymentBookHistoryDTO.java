package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.domain.PaymentBookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 주문 목록이나 주문 상세 정보 화면에 뿌려줄때 사용할 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBookHistoryDTO {
    private Long orderBookNo; // auto_increment
    private Long orderBookId; // 2024040300000
    private PaymentBookStatus paymentBookStatus;
    private String orderBookMethod;
    private LocalDateTime createDate;

    private String userId;
    private String userName;

    private Long bookId;
    private String bookTitle;
    private String storeTitle;
    private String bookPub;
    private String bookEbookPrice;
    private String bookWriter;
    private String bookImgStored;

    // private String reviewStatus;

    // Entity -> DTO
    public PaymentBookHistoryDTO(PaymentBookHistory paymentBookHistory) {
        this.orderBookNo = paymentBookHistory.getPaymentBook().getOrderBookNo();
        this.orderBookId = paymentBookHistory.getPaymentBook().getOrderBookId();
        this.paymentBookStatus = paymentBookHistory.getPaymentBook().getPaymentBookStatus();
        this.orderBookMethod = paymentBookHistory.getPaymentBook().getOrderBookMethod();
        this.createDate = paymentBookHistory.getCreateDate();

        this.userName = paymentBookHistory.getPaymentBook().getMember().getUserName();
        this.userId = paymentBookHistory.getPaymentBook().getMember().getUserId();

        this.bookId = paymentBookHistory.getBook().getBookId();
        this.bookTitle = paymentBookHistory.getBook().getBookTitle();
        this.storeTitle = paymentBookHistory.getBook().getStore().getStoreTitle();
        this.bookPub = paymentBookHistory.getBook().getBookPub();
        this.bookEbookPrice = paymentBookHistory.getBook().getBookEbookPrice();
        this.bookWriter = paymentBookHistory.getBook().getBookWriter();
        this.bookImgStored = paymentBookHistory.getBook().getBookImgStored();
    }
}
