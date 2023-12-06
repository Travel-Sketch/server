package com.travelsketch.travel.api.service.attraction;

import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
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

    public List<SidoResponse> searchSidos() {
        return null;
    }
}
