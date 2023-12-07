package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.api.controller.attraction.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AttractionQueryService {

    public List<AttractionResponse> searchAttraction(int sidoId, int gugunId, int typeId, String query) {
        return null;
    }
}
