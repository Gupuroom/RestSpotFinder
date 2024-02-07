package com.restspotfinder.route.service;

import com.restspotfinder.route.domain.TempPoint;
import com.restspotfinder.route.repository.TempPointRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TempPointService {
    private final TempPointRepository tempRouteRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Transactional
    public List<TempPoint> create(Coordinate[] coordinates, long routeId) {
        return tempRouteRepository.saveAll(TempPoint.fromList(geometryFactory, coordinates, routeId));
    }
}
