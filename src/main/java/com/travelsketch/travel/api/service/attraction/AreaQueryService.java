package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.api.controller.attraction.response.GugunResponse;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import com.travelsketch.travel.domain.attraction.Gugun;
import com.travelsketch.travel.domain.attraction.Sido;
import com.travelsketch.travel.domain.attraction.repository.GugunRepository;
import com.travelsketch.travel.domain.attraction.repository.SidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AreaQueryService {

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    public List<SidoResponse> searchSidos() {
        List<Sido> findSidos = sidoRepository.findAll();

        return findSidos.stream()
            .map(SidoResponse::of)
            .toList();
    }

    public List<GugunResponse> searchGuguns(Long sidoId) {
        List<Gugun> findGuguns = gugunRepository.findBySidoId(sidoId);

        return findGuguns.stream()
            .map(GugunResponse::of)
            .toList();
    }
}
