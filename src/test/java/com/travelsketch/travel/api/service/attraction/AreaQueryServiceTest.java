package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import com.travelsketch.travel.domain.attraction.Sido;
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

    private Sido saveSido(String name) {
        Sido sido = Sido.builder()
            .name(name)
            .build();
        return sidoRepository.save(sido);
    }
}