package com.jpa4.pj1984.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="persistent_logins")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistenceLogins implements Serializable {

    @Id
    @Column(length = 64)
    private String series;
    @Column(length = 64, nullable = false)
    private String username;
    @Column(length = 64, nullable = false)
    private String token;
    @Column(name = "last_used", nullable = false)
    private LocalDateTime lastUsed;

}