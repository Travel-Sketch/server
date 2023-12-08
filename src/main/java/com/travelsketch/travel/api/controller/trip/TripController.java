package com.travelsketch.travel.api.controller.trip;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.trip.request.CreateTripRequest;
import com.travelsketch.travel.api.controller.trip.request.ModifyTripRequest;
import com.travelsketch.travel.api.controller.trip.response.CreateTripResponse;
import com.travelsketch.travel.api.controller.trip.response.ModifyTripResponse;
import com.travelsketch.travel.api.controller.trip.response.RemoveTripResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/trips")
public class TripController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateTripResponse> createTrip(@RequestBody CreateTripRequest request) {
        CreateTripResponse response = CreateTripResponse.builder()
            .tripId(1L)
            .title("나의 여행 계획 제목")
            .attractionCount(3)
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return created(response);
    }

    @PatchMapping("/{tripId}")
    public ApiResponse<ModifyTripResponse> modifyTrip(
        @PathVariable Long tripId,
        @RequestBody ModifyTripRequest request
    ) {
        ModifyTripResponse response = ModifyTripResponse.builder()
            .tripId(1L)
            .title("수정된 여행 계획 제목")
            .attractionCount(3)
            .modifiedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }

    @DeleteMapping("/{tripId}")
    public ApiResponse<RemoveTripResponse> removeTrip(@PathVariable Long tripId) {
        RemoveTripResponse response = RemoveTripResponse.builder()
            .tripId(1L)
            .title("삭제된 여행 계획 제목")
            .removedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }
}
