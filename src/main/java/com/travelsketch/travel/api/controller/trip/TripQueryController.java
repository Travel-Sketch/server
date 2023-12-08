package com.travelsketch.travel.api.controller.trip;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.trip.response.TripDetailResponse;
import com.travelsketch.travel.api.controller.trip.response.TripResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/trips")
public class TripQueryController {

    @GetMapping
    public ApiResponse<PageResponse<TripResponse>> searchTrips(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        TripResponse response = TripResponse.builder()
            .tripId(1L)
            .title("나의 여행 계획 제목")
            .writer("카리나")
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        PageImpl<TripResponse> content = new PageImpl<>(List.of(response), pageRequest, 1);

        PageResponse<TripResponse> result = new PageResponse<>(content);

        return ok(result);
    }

    @GetMapping("/{tripId}")
    public ApiResponse<TripDetailResponse> searchTrip(@PathVariable Long tripId) {
        TripDetailResponse response = TripDetailResponse.builder()
            .tripId(1L)
            .title("나의 여행 계획 제목")
            .writer("카리나")
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }
}
