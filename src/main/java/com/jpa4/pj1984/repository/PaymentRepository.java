package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
