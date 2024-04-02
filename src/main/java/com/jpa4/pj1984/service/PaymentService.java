package com.jpa4.pj1984.service;

import com.jpa4.pj1984.DTO.PaymentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    public void saveOrder(Long userNo, List<PaymentDTO> paymentDTOList) {

    }
}
