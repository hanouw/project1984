package com.jpa4.pj1984.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    USER("ROLE_USER"), STORE("ROLE_STORE");
    private final String value;
}
