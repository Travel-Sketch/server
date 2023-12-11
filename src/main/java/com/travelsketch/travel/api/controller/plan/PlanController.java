package com.travelsketch.travel.api.controller.plan;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.plan.request.CreatePlanRequest;
import com.travelsketch.travel.api.controller.plan.request.ModifyPlanRequest;
import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.api.controller.plan.response.ModifyPlanResponse;
import com.travelsketch.travel.api.controller.plan.response.RemovePlanResponse;
import com.travelsketch.travel.api.service.plan.PlanService;
import com.travelsketch.travel.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/plans")
public class PlanController {

    private final PlanService planService;
    private final SecurityUtils securityUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreatePlanResponse> createPlan(@Valid @RequestBody CreatePlanRequest request) {
        if (request.getAttractions().size() > 10) {
            throw new IllegalArgumentException("등록할 수 있는 관광지 수는 최대 10개입니다.");
        }

        String email = securityUtils.getCurrentEmail();

        CreatePlanResponse response = planService.createPlan(email, request.getTitle(), request.getAttractions());

        return created(response);
    }

    @PatchMapping("/{planId}")
    public ApiResponse<ModifyPlanResponse> modifyPlan(
        @PathVariable Long planId,
        @RequestBody ModifyPlanRequest request
    ) {
        ModifyPlanResponse response = ModifyPlanResponse.builder()
            .planId(1L)
            .title("수정된 여행 계획 제목")
            .attractionCount(3)
            .modifiedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }

    @DeleteMapping("/{planId}")
    public ApiResponse<RemovePlanResponse> removePlan(@PathVariable Long planId) {
        RemovePlanResponse response = RemovePlanResponse.builder()
            .planId(1L)
            .title("삭제된 여행 계획 제목")
            .removedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }
}
