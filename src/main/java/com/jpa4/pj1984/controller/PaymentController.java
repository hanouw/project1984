package com.jpa4.pj1984.controller;


import com.jpa4.pj1984.dto.PaymentDTO;
import com.jpa4.pj1984.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/order")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    @PostMapping("/book")
    public ResponseEntity<String> paymentComplete(@RequestBody PaymentDTO paymentDTO
                                                  // @AuthenticationPrincipal CustomMember customMember
    ) throws IOException {

        Long userNo = paymentDTO.getUserNo(); // TODO 추후 수정
        paymentService.saveOrder(userNo, paymentDTO);
        // log.info("결제 성공 : 주문 번호 {}", orderNumber);
        return ResponseEntity.ok().build();
        }


    @PostMapping("/book/validation/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid) throws IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }

}
