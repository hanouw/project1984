package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) //
@MappedSuperclass // DB 테이블과 관계없는 부모 클래스
@Getter
public abstract class TimeEntity {
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        lastModifiedDate = now;
    }
    @PreUpdate // update 하기 전 실행
    public void preUpdate(){
        lastModifiedDate = LocalDateTime.now();
    }
}

