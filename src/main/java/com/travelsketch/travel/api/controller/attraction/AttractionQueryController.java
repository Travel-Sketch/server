package com.travelsketch.travel.api.controller.attraction;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.attraction.response.AttractionResponse;
import com.travelsketch.travel.api.controller.attraction.response.GugunResponse;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import com.travelsketch.travel.api.service.attraction.AreaQueryService;
import com.travelsketch.travel.api.service.attraction.AttractionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class AttractionQueryController {

    private final AreaQueryService areaQueryService;
    private final AttractionQueryService attractionQueryService;

    @GetMapping("/sidos")
    public ApiResponse<List<SidoResponse>> searchSidos() {

        List<SidoResponse> responses = areaQueryService.searchSidos();

        return ok(responses);
    }

    @GetMapping("/sidos/{sidoId}/guguns")
    public ApiResponse<List<GugunResponse>> searchGuguns(@PathVariable Integer sidoId) {

        List<GugunResponse> responses = areaQueryService.searchGuguns(sidoId);

        return ok(responses);
    }

    @GetMapping("/attractions")
    public ApiResponse<List<AttractionResponse>> searchAttractions(
        @RequestParam(defaultValue = "1") Integer sidoId,
        @RequestParam(defaultValue = "1") Integer gugunId,
        @RequestParam(defaultValue = "12") Integer typeId,
        @RequestParam(defaultValue = "") String query
    ) {

        List<AttractionResponse> responses = attractionQueryService.searchAttraction(sidoId, gugunId, typeId, query);

        return ok(responses);
    }
}
