package com.jpa4.pj1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"), MEMBERSHIP("ROLE_MEMBERSHIP"), STORE("ROLE_STORE");
    private final String value;
}
