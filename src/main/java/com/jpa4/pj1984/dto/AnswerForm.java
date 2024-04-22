package com.jpa4.pj1984.dto;

import com.jpa4.pj1984.domain.Answer;
import com.jpa4.pj1984.domain.Inquiry;
import com.jpa4.pj1984.domain.Store;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class AnswerForm {
    private Long answerId;
    private String answerTitle;
    private String answerDetail;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;

    // DTO -> Entity
    public Answer toEntity() {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setAnswerTitle(answerTitle);
        answer.setAnswerDetail(answerDetail);
        return answer;
    }
}
