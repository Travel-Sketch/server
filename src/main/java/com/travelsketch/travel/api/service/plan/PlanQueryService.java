package com.travelsketch.travel.api.service.plan;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import com.travelsketch.travel.domain.plan.repository.PlanQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanQueryService {

    private final PlanQueryRepository planQueryRepository;

    public PageResponse<PlanResponse> searchByCond(String query, PageRequest pageRequest) {
        return null;
    }
}
