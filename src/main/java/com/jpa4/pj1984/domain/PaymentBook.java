package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @Column(nullable = false)
    private String totalAmount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderBookHistoryId")
    private List<PaymentBookHistory> orderBookHistories = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_book_history_id")
//    private List<PaymentBookHistory> orderBookHistories;
}
