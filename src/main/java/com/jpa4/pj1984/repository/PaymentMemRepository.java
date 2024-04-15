package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentMem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMemRepository extends JpaRepository<PaymentMem, Long>, PaymentMemCustomRepository {
}
