package com.travelsketch.travel.api.service.plan;

import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.domain.attraction.Attraction;
import com.travelsketch.travel.domain.attraction.repository.AttractionRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.plan.Plan;
import com.travelsketch.travel.domain.plan.repository.PlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    public CreatePlanResponse createPlan(String email, String title, List<Integer> attractionIds) {
        Member member = getMember(email);

        List<Attraction> attractions = attractionRepository.findByIdIn(attractionIds);

        if (attractionIds.size() != attractions.size()) {
            throw new NoSuchElementException("존재하지 않는 관광지 정보입니다.");
        }

        Plan plan = Plan.createPlan(title, member, attractions);

        return CreatePlanResponse.of(plan);
    }

    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }
}
