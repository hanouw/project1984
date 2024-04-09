package com.jpa4.repository;

import com.jpa4.pj1984.domain.PaymentBookHistory;
import com.jpa4.pj1984.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PaymentBookServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testSignUp() {

        PaymentBookHistory history = new PaymentBookHistory();
        System.out.println("---------"+ history);
    }
}
