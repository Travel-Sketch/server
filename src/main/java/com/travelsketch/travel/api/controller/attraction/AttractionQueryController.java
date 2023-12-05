package com.travelsketch.travel.api.controller.attraction;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/sidos")
public class AttractionQueryController {

    @GetMapping
    public ApiResponse<List<SidoResponse>> searchSidos() {
        SidoResponse response = SidoResponse.builder()
            .sidoId(1L)
            .name("서울")
            .build();
        return ok(List.of(response));
    }
}
