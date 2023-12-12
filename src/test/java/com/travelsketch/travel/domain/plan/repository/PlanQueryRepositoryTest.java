package com.travelsketch.travel.domain.plan.repository;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.plan.response.PlanDetailResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import com.travelsketch.travel.domain.attraction.Attraction;
import com.travelsketch.travel.domain.attraction.Gugun;
import com.travelsketch.travel.domain.attraction.Sido;
import com.travelsketch.travel.domain.attraction.repository.AttractionRepository;
import com.travelsketch.travel.domain.attraction.repository.GugunRepository;
import com.travelsketch.travel.domain.attraction.repository.SidoRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.plan.Plan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.travelsketch.travel.domain.attraction.AttractionType.ATTRACTION;
import static org.assertj.core.api.Assertions.assertThat;

class PlanQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private PlanQueryRepository planQueryRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private SidoRepository sidoRepository;

    @Autowired
    private GugunRepository gugunRepository;

    @DisplayName("검색 조건에 맞는 여행 계획을 조회한다.")
    @Test
    void findByCond() {
        //given
        Member member = savedMember();
        Sido sido = saveSido();
        Gugun gugun = saveGugun(sido);
        Attraction attraction = saveAttraction(sido, gugun, "롯데월드");
        Plan plan1 = savePlan("잠실 롯데월드 여행 계획입니다.", member, List.of(attraction));
        Plan plan2 = savePlan("부산 롯데월드 여행 계획입니다.", member, List.of(attraction));
        Plan plan3 = savePlan("용산 에버랜드 여행 계획입니다.", member, List.of(attraction));
        Plan plan4 = savePlan("롯데월드 여행 계획입니다.", member, List.of(attraction));
        plan4.remove();

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        List<PlanResponse> responses = planQueryRepository.findByCond("롯데", pageRequest);

        //then
        assertThat(responses).hasSize(2)
            .extracting("title")
            .containsExactlyInAnyOrder(
                "잠실 롯데월드 여행 계획입니다.", "부산 롯데월드 여행 계획입니다."
            );
    }

    @DisplayName("검색 조건에 맞는 여행 계획을 갯수를 조회한다.")
    @Test
    void findCountByCond() {
        //given
        Member member = savedMember();
        Sido sido = saveSido();
        Gugun gugun = saveGugun(sido);
        Attraction attraction = saveAttraction(sido, gugun, "롯데월드");
        Plan plan1 = savePlan("잠실 롯데월드 여행 계획입니다.", member, List.of(attraction));
        Plan plan2 = savePlan("부산 롯데월드 여행 계획입니다.", member, List.of(attraction));
        Plan plan3 = savePlan("용산 에버랜드 여행 계획입니다.", member, List.of(attraction));
        Plan plan4 = savePlan("롯데월드 여행 계획입니다.", member, List.of(attraction));
        plan4.remove();

        //when
        int count = planQueryRepository.findCountByCond("롯데");

        //then
        assertThat(count).isEqualTo(2);
    }

    @DisplayName("아이디로 여행 계획을 조회할 수 있다.")
    @Test
    void findById() {
        //given
        Member member = savedMember();
        Sido sido = saveSido();
        Gugun gugun = saveGugun(sido);
        Attraction attraction1 = saveAttraction(sido, gugun, "롯데월드");
        Attraction attraction2 = saveAttraction(sido, gugun, "롯데타워");
        Attraction attraction3 = saveAttraction(sido, gugun, "석촌호수");
        Plan plan = savePlan("잠실 여행 계획입니다.", member, List.of(attraction1, attraction2, attraction3));

        //when
        Optional<PlanDetailResponse> findResponse = planQueryRepository.findById(plan.getId());

        //then
        assertThat(findResponse).isPresent();
        assertThat(findResponse.get().getTitle()).isEqualTo("잠실 여행 계획입니다.");
    }

    private Member savedMember() {
        Member member = Member.builder()
            .email("karina@naver.com")
            .pwd(passwordEncoder.encode("karina1234!"))
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .role(Role.USER)
            .build();
        return memberRepository.save(member);
    }

    private Sido saveSido() {
        Sido sido = Sido.builder()
            .name("서울")
            .build();
        return sidoRepository.save(sido);
    }

    private Gugun saveGugun(Sido sido) {
        Gugun gugun = Gugun.builder()
            .name("송파구")
            .sido(sido)
            .build();
        return gugunRepository.save(gugun);
    }

    private Attraction saveAttraction(Sido sido, Gugun gugun, String title) {
        Attraction attraction = Attraction.builder()
            .attractionTypeId(ATTRACTION.getCode())
            .title(title)
            .zipcode("12345")
            .address("address")
            .tel("02-1234-1234")
            .image("image.png")
            .longitude(37.52251412000000000)
            .latitude(128.29191150000000000)
            .mlevel("3")
            .sido(sido)
            .gugun(gugun)
            .build();
        return attractionRepository.save(attraction);
    }

    private Plan savePlan(String title, Member member, List<Attraction> attractions) {
        Plan plan = Plan.createPlan(title, member, attractions);
        return planRepository.save(plan);
    }
}