package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class PaymentBookHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderBookHistoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_book_id")
    private Payment payment;
}
