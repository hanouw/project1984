package com.jpa4.pj1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMemStatus {
    COMPLETE("STATUS_ON"), OFF("STATUS_OFF");
    private final String value;
}
