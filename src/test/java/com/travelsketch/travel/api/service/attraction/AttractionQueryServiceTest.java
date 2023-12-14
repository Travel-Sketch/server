package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.attraction.response.AttractionResponse;
import com.travelsketch.travel.domain.attraction.Attraction;
import com.travelsketch.travel.domain.attraction.AttractionType;
import com.travelsketch.travel.domain.attraction.Gugun;
import com.travelsketch.travel.domain.attraction.Sido;
import com.travelsketch.travel.domain.attraction.repository.AttractionRepository;
import com.travelsketch.travel.domain.attraction.repository.GugunRepository;
import com.travelsketch.travel.domain.attraction.repository.SidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.travelsketch.travel.domain.attraction.AttractionType.ATTRACTION;
import static com.travelsketch.travel.domain.attraction.AttractionType.CULTURE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class AttractionQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private AttractionQueryService attractionQueryService;

    @Autowired
    private AttractionRepository attractionRepository;

    @Autowired
    private SidoRepository sidoRepository;

    @Autowired
    private GugunRepository gugunRepository;

    @DisplayName("시도, 구군, 관광지 유형 아이디, 검색 쿼리를 입력 받아 관광지 목록을 조회할 수 있다.")
    @Test
    void searchAttractions() {
        //given
        Sido sido = saveSido("서울");
        Gugun gugun = saveGugun("송파구", sido);
        Attraction attraction1 = saveAttraction(ATTRACTION, sido, gugun, "롯데월드");
        Attraction attraction2 = saveAttraction(CULTURE, sido, gugun, "롯데월드");

        //when
        List<AttractionResponse> responses = attractionQueryService.searchAttraction(sido.getId(), gugun.getId(), ATTRACTION.getCode(), "롯데");

        //then
        assertThat(responses).hasSize(1)
            .extracting("type", "title")
            .containsExactlyInAnyOrder(
                tuple(ATTRACTION.getText(), "롯데월드")
            );
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

    private Attraction saveAttraction(AttractionType type, Sido sido, Gugun gugun, String title) {
        Attraction attraction = Attraction.builder()
            .attractionTypeId(type.getCode())
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
}