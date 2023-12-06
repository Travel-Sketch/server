package com.travelsketch.travel.api.controller.attraction;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.attraction.response.AttractionResponse;
import com.travelsketch.travel.api.controller.attraction.response.GugunResponse;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import com.travelsketch.travel.api.service.attraction.AreaQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/sidos")
public class AttractionQueryController {

    private final AreaQueryService areaQueryService;

    @GetMapping
    public ApiResponse<List<SidoResponse>> searchSidos() {

        List<SidoResponse> responses = areaQueryService.searchSidos();

        return ok(responses);
    }

    @GetMapping("/{sidoId}/guguns")
    public ApiResponse<List<GugunResponse>> searchGuguns(@PathVariable Long sidoId) {
        GugunResponse response = GugunResponse.builder()
            .gugunId(1L)
            .name("강남구")
            .build();
        return ok(List.of(response));
    }

    @GetMapping("/{sidoId}/guguns/{gugunId}/attractions")
    public ApiResponse<List<AttractionResponse>> searchAttractions(@PathVariable Long sidoId, @PathVariable Long gugunId) {
        AttractionResponse response = AttractionResponse.builder()
            .attractionId(126498L)
            .title("롯데월드")
            .zipcode("5554")
            .address("서울특별시 송파구 올림픽로 240")
            .tel(null)
            .image("http://tong.visitkorea.or.kr/cms/resource/36/2540136_image2_1.JPG")
            .longitude(127.09793950000000000)
            .latitude(37.51122948000000000)
            .build();
        return ok(List.of(response));
    }
}
