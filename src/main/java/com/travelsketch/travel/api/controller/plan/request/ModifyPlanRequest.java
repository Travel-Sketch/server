package com.travelsketch.travel.api.controller.plan.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ModifyPlanRequest {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "제목은 최대 50자입니다.")
    private String title;

    @NotEmpty(message = "관광지 정보는 최소 1개 이상 입력해주세요.")
    private List<Integer> attractions;

    @Builder
    private ModifyPlanRequest(String title, List<Integer> attractions) {
        this.title = title;
        this.attractions = attractions;
    }
}
