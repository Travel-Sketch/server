package com.travelsketch.travel.api.controller.qna.request;

import com.travelsketch.travel.api.service.qna.dto.CreateQuestionDto;
import com.travelsketch.travel.domain.qna.QnaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateQuestionRequest {

    @NotNull(message = "질문 유형은 필수입니다.")
    private QnaType type;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "제목은 최대 50자입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private String pwd;

    @Builder
    private CreateQuestionRequest(QnaType type, String title, String content, String pwd) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.pwd = pwd;
    }

    public CreateQuestionDto toDto() {
        return CreateQuestionDto.builder()
            .type(this.type)
            .title(this.title)
            .content(this.content)
            .pwd(this.pwd)
            .build();
    }
}
