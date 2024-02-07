package com.restspotfinder.route.controller;

import com.restspotfinder.route.domain.OptionCode;
import com.restspotfinder.route.domain.TempRoute;
import com.restspotfinder.route.response.TempRouteResponse;
import com.restspotfinder.route.service.NCPGeoService;
import com.restspotfinder.route.service.TempPointService;
import com.restspotfinder.route.service.TempRouteService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/route")
public class TempRouteController {
    private final NCPGeoService ncpGeoService;
    private final TempRouteService tempRouteService;
    private final TempPointService tempPointService;

    @GetMapping
    public ResponseEntity getRoute(@RequestParam String start, @RequestParam String goal) {
        Map<OptionCode, Coordinate[]> coordinateMap = ncpGeoService.getRouteData(start, goal);
        List<TempRouteResponse> responseList = coordinateMap.entrySet().stream().map(entry -> {
            TempRoute savedTempRoute = tempRouteService.create(entry.getValue(), entry.getKey()); // 경로 저장
            tempPointService.create(entry.getValue(), savedTempRoute.getRouteId()); // 휴게소와 좌표 비교할 Point List 저장
            return TempRouteResponse.from(savedTempRoute);
        }).toList();

        return ResponseEntity.ok().body(responseList);
    }
}