package com.restspotfinder.route.service;

import com.restspotfinder.route.domain.TempPoint;
import com.restspotfinder.route.domain.TempRoute;
import com.restspotfinder.route.repository.TempPointRepository;
import com.restspotfinder.route.repository.TempRouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class NCPGeoServiceTest {
    @Autowired
    NCPGeoService NCPGeoService;
    @Autowired
    TempRouteRepository tempRouteRepository;
    @Autowired
    TempPointRepository tempPointRepository;


    @Test
    void getCoordinate() {
        // given
        String address = "충북 옥천군 옥천읍 중앙로 99";

        // when
        Coordinate coordinate = NCPGeoService.getCoordinate(address);

        // then
        System.out.println("coordinate = " +  coordinate);
    }

    @Test
    void getRouteData() {
        // given
        String goal = "127.1285607,37.4319958"; // 모란 오라타워
        String start = "127.1468225,36.8123208"; // 천안역
//        String start = "129.0449881,35.1177214"; // 부산역
//        String start = "127.4321036,36.3344179"; // 대전역
//        String start = "127.5714191,36.3064369"; // 옥천구청
//        String start = "127.8280515,36.2640232"; // 구룡초등학교
//        String goal = "127.9112630,36.2245680"; // 황간역

        // when
        Coordinate[] coordinates = NCPGeoService.getRouteData(start, goal);

        // then
        GeometryFactory geometryFactory = new GeometryFactory();

        TempRoute tempRoute = TempRoute.from(geometryFactory, coordinates);
        TempRoute savedTempRoute = tempRouteRepository.save(tempRoute);

        List<TempPoint> tempPointList = TempPoint.fromList(geometryFactory, coordinates, savedTempRoute.getRouteId());
        tempPointRepository.saveAll(tempPointList);
    }
}