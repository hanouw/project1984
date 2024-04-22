package com.jpa4.pj1984.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Membership extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipNo;
    private String price;

    public Membership(long l) {
        this.membershipNo = l;
    }

    public Membership() {

    }
}
