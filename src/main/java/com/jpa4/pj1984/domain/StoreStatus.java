package com.jpa4.pj1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum StoreStatus {
    STORE("STATUS_STORE"), NOTAPPROVE("STATUS_NOTAPPROVE"), QUIT("STATUS_QUIT"), BANNED("STATUS_BANNED") ;
    private final String value;
}


