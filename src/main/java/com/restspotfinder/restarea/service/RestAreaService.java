package com.restspotfinder.restarea.service;

import com.restspotfinder.restarea.domain.RestArea;
import com.restspotfinder.restarea.repository.RestAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RestAreaService {
    private final RestAreaRepository restAreaRepository;

    public RestArea save(RestArea restArea){
        return restAreaRepository.save(restArea);
    }

    public RestArea getOneById(long restAreaId){
        return restAreaRepository.findById(restAreaId)
                .orElseThrow(() -> new NullPointerException("[RestArea] restAreaId : " + restAreaId));
    }

    public List<RestArea> getNearbyRoutes(long routeId) {
        return restAreaRepository.findNearbyRoutes(routeId).stream().distinct().toList();
    }
}
