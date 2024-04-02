package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Order extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderBookId;
    @OneToOne
    @JoinColumn(name = "userNo")
    private Member member;
    @Column(nullable = false)
    private String orderBookMethod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderBookStatus orderBookStatus;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_book_history")
    private List<OrderBookHistory> orderBookHistories;
}
