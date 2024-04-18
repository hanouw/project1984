package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Answer;
import com.jpa4.pj1984.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long answerId;
    private String answerTitle;
    private String answerDetail;
    private Store store;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    // Entity -> DTO
    public AnswerDTO(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.answerTitle = answer.getAnswerTitle();
        this.answerDetail = answer.getAnswerDetail();
        this.store = answer.getStore();
    }
}