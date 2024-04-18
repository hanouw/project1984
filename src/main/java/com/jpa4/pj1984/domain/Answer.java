package com.jpa4.pj1984.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer extends TimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private String answerTitle;
    private String answerDetail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId")
    private Store store;
    @OneToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;
}
