package com.restspotfinder.route.service;

import com.restspotfinder.route.domain.OptionCode;
import com.restspotfinder.route.domain.TempRoute;
import com.restspotfinder.route.repository.TempRouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempRouteService {
    private final TempRouteRepository tempRouteRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public TempRoute create(Coordinate[] coordinates, OptionCode optionCode) {
        return tempRouteRepository.save(TempRoute.from(geometryFactory, coordinates, optionCode));
    }
}
