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

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;

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
        @Valid @RequestBody ModifyPlanRequest request
    ) {
        if (request.getAttractions().size() > 10) {
            throw new IllegalArgumentException("등록할 수 있는 관광지 수는 최대 10개입니다.");
        }

        ModifyPlanResponse response = planService.modifyPlan(planId, request.getTitle(), request.getAttractions());

        return ok(response);
    }

    @DeleteMapping("/{planId}")
    public ApiResponse<RemovePlanResponse> removePlan(@PathVariable Long planId) {
        RemovePlanResponse response = planService.removePlan(planId);

        return ok(response);
    }
}
