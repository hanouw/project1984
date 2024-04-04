package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Payment extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderBookNo;
    @Column(nullable = false)
    private Long orderBookId;
    @OneToOne
    @JoinColumn(name = "userNo")
    private Member member;
    @Column(nullable = false)
    private String orderBookMethod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentBookStatus paymentBookStatus;
    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    private List<PaymentBookHistory> orderBookHistories;
}
