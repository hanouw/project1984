package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PaymentMem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderMembershipNo; // auto_increment

    @Column(nullable = false)
    private Long orderMembershipId; // 2024041200000

    @Column(nullable = false)
    private String orderMembershipMethod;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMemStatus paymentMemStatus;

    @Column(updatable = false) @CreatedDate
    private LocalDateTime membershipStartDate;

    @Column(updatable = false)
    private LocalDateTime membershipEndDate;

    @PrePersist
    private void calculateMembershipEndDate() {
        if (membershipStartDate != null) {
            this.membershipEndDate = this.membershipStartDate.plusDays(30);
        }
    }

}
