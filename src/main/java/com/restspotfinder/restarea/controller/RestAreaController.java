package com.restspotfinder.restarea.controller;

import com.restspotfinder.restarea.domain.RestArea;
import com.restspotfinder.restarea.response.RestAreaResponse;
import com.restspotfinder.restarea.service.RestAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restarea")
public class RestAreaController {
    private final RestAreaService restAreaService;

    @GetMapping
    public ResponseEntity getRestAreaByRouteId(@RequestParam long routeId) {
        List<RestArea> restAreaList = restAreaService.getNearbyRoutes(routeId);
        return ResponseEntity.ok().body(RestAreaResponse.fromList(restAreaList));
    }
}