package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.api.controller.attraction.response.AttractionResponse;
import com.travelsketch.travel.domain.attraction.Attraction;
import com.travelsketch.travel.domain.attraction.repository.AttractionQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttractionQueryService {

    private final AttractionQueryRepository attractionQueryRepository;

    public List<AttractionResponse> searchAttraction(int sidoId, int gugunId, int typeId, String query) {
        List<Attraction> attractions = attractionQueryRepository.findByCond(sidoId, gugunId, typeId, query);

        return attractions.stream()
            .map(AttractionResponse::of)
            .toList();
    }
}
