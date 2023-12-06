package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.attraction.response.GugunResponse;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import com.travelsketch.travel.domain.attraction.Gugun;
import com.travelsketch.travel.domain.attraction.Sido;
import com.travelsketch.travel.domain.attraction.repository.GugunRepository;
import com.travelsketch.travel.domain.attraction.repository.SidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AreaQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private AreaQueryService areaQueryService;

    @Autowired
    private SidoRepository sidoRepository;

    @Autowired
    private GugunRepository gugunRepository;

    @DisplayName("모든 시도 목록들 조회할 수 있다.")
    @Test
    void searchSidos() {
        //given
        Sido sido1 = saveSido("서울");
        Sido sido2 = saveSido("인천");
        Sido sido3 = saveSido("광주");

        //when
        List<SidoResponse> responses = areaQueryService.searchSidos();

        //then
        assertThat(responses).hasSize(3)
            .extracting("name")
            .containsExactlyInAnyOrder("서울", "인천", "광주");
    }

    @DisplayName("시도 아이디를 입력 받아 모든 구군을 조회할 수 있다,")
    @Test
    void searchGuguns() {
        //given
        Sido sido = saveSido("서울");
        Gugun gugun1 = saveGugun("강남구", sido);
        Gugun gugun2 = saveGugun("강동구", sido);
        Gugun gugun3 = saveGugun("강북구", sido);

        //when
        List<GugunResponse> responses = areaQueryService.searchGuguns(sido.getId());

        //then
        assertThat(responses).hasSize(3)
            .extracting("name")
            .containsExactlyInAnyOrder("강남구", "강동구", "강북구");
    }

    private Sido saveSido(String name) {
        Sido sido = Sido.builder()
            .name(name)
            .build();
        return sidoRepository.save(sido);
    }

    private Gugun saveGugun(String name, Sido sido) {
        Gugun gugun = Gugun.builder()
            .name(name)
            .sido(sido)
            .build();
        return gugunRepository.save(gugun);
    }
}