package com.jpa4.pj1984.repository;

import com.jpa4.pj1984.domain.PaymentBookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentBookHistoryRepository extends JpaRepository<PaymentBookHistory, Long> {
}
