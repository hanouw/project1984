package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class PaymentBook extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderBookNo;

    @Column(nullable = false)
    private Long orderBookId;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private Member member;

    @Column(nullable = false)
    private String orderBookMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentBookStatus paymentBookStatus;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_book_history_id")
//    private List<PaymentBookHistory> orderBookHistories;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_book_no") // 외래 키를 PaymentBookHistory의 기본 키로 지정
    private List<PaymentBookHistory> orderBookHistories;
}