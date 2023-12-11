package com.travelsketch.travel.api.controller.plan.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ModifyPlanRequest {

    private String title;
    private List<Integer> attractions;

    @Builder
    private ModifyPlanRequest(String title, List<Integer> attractions) {
        this.title = title;
        this.attractions = attractions;
    }
}