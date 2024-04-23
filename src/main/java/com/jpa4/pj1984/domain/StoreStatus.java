package com.jpa4.pj1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum StoreStatus {
    STORE("STATUS_STORE"), QUIT("STATUS_QUIT"), ADMIN("STATUS_ADMIN") ;
    private final String value;
}


