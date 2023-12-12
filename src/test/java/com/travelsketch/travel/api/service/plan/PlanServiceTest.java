package com.travelsketch.travel.api.service.plan;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.api.controller.plan.response.ModifyPlanResponse;
import com.travelsketch.travel.api.controller.plan.response.RemovePlanResponse;
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
import com.travelsketch.travel.domain.plan.repository.PlanRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.travelsketch.travel.domain.attraction.AttractionType.ATTRACTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlanServiceTest extends IntegrationTestSupport {

    @Autowired
    private PlanService planService;

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

    @DisplayName("입력 받은 관광지 아이디 리스트에 존재하지 않는 관광지가 있으면 예외가 발생한다.")
    @Test
    void createPlanNoSuchAttraction() {
        //given
        Member member = savedMember();

        List<Integer> attractionIds = List.of(1);

        //when //then
        assertThatThrownBy(() -> planService.createPlan("karina@naver.com", "나의 여행 계획 제목입니다.", attractionIds))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("존재하지 않는 관광지 정보입니다.");
    }

    @DisplayName("제목과 관광지 아이디 리스트를 입력 받아 여행 계획을 등록할 수 있다.")
    @Test
    void createPlan() {
        //given
        Member member = savedMember();
        Sido seoul = saveSido();
        Gugun songpa = saveGugun(seoul);

        Attraction attraction1 = saveAttraction(seoul, songpa, "관광지 1");
        Attraction attraction2 = saveAttraction(seoul, songpa, "관광지 2");
        Attraction attraction3 = saveAttraction(seoul, songpa, "관광지 3");

        List<Integer> attractionIds = List.of(attraction1.getId(), attraction2.getId(), attraction3.getId());

        //when
        CreatePlanResponse response = planService.createPlan("karina@naver.com", "나의 여행 계획 제목입니다.", attractionIds);

        //then
        assertThat(response.getAttractionCount()).isEqualTo(3);
    }

    @DisplayName("입력 받은 계획 아이디와 일치하는 계획이 존재하지 않으면 예외가 발생한다.")
    @Test
    void modifyPlanNoSuchPlan() {
        //given
        List<Integer> attractionIds = List.of(1);

        //when //then
        assertThatThrownBy(() -> planService.modifyPlan(1L, "수정된 나의 여행 계획 제목입니다.", attractionIds))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 계획입니다.");
    }

    @DisplayName("입력 받은 관광지 아이디 리스트에 존재하지 않는 관광지가 있으면 예외가 발생한다.")
    @Test
    void modifyPlanNoSuchAttraction() {
        //given
        Member member = savedMember();
        Sido seoul = saveSido();
        Gugun songpa = saveGugun(seoul);
        Attraction attraction = saveAttraction(seoul, songpa, "관광지 1");

        Plan plan = savePlan(member, List.of(attraction));

        List<Integer> attractionIds = List.of(100);
        //when //then
        assertThatThrownBy(() -> planService.modifyPlan(plan.getId(), "수정된 나의 여행 계획 제목입니다.", attractionIds))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("존재하지 않는 관광지 정보입니다.");

    }

    @DisplayName("제목과 관광지 아이디 리스트를 입력 받아 여행 계획을 수정할 수 있다.")
    @Test
    void modifyPlan() {
        //given
        Member member = savedMember();
        Sido seoul = saveSido();
        Gugun songpa = saveGugun(seoul);
        Attraction attraction1 = saveAttraction(seoul, songpa, "관광지 1");

        Plan plan = savePlan(member, List.of(attraction1));

        Attraction attraction2 = saveAttraction(seoul, songpa, "관광지 2");
        Attraction attraction3 = saveAttraction(seoul, songpa, "관광지 3");
        List<Integer> attractionIds = List.of(attraction2.getId(), attraction3.getId());

        //when
        ModifyPlanResponse response = planService.modifyPlan(plan.getId(), "수정된 나의 여행 계획 제목입니다.", attractionIds);

        //then
        assertThat(response.getAttractionCount()).isEqualTo(2);
    }

    @DisplayName("입력 받은 계획 아이디와 일치하는 계획이 존재하지 않으면 예외가 발생한다.")
    @Test
    void removePlanNoSuchPlan() {
        //given //when //then
        assertThatThrownBy(() -> planService.removePlan(1L))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 계획입니다.");
    }

    @DisplayName("여행 계획 아이디를 입력 받아 계획을 삭제할 수 있다.")
    @Test
    void removePlan() {
        //given
        Member member = savedMember();
        Sido seoul = saveSido();
        Gugun songpa = saveGugun(seoul);
        Attraction attraction1 = saveAttraction(seoul, songpa, "관광지 1");

        Plan plan = savePlan(member, List.of(attraction1));

        //when
        RemovePlanResponse response = planService.removePlan(plan.getId());

        //then
        Optional<Plan> findPlan = planRepository.findById(response.getPlanId());
        assertThat(findPlan).isPresent();
        assertThat(findPlan.get().getIsDeleted()).isTrue();
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

    private Plan savePlan(Member member, List<Attraction> attractions) {
        Plan plan = Plan.createPlan("나의 여행 계획 제목입니다.", member, attractions);
        return planRepository.save(plan);
    }
}