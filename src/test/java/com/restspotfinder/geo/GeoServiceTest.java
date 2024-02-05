package com.restspotfinder.geo;

import com.restspotfinder.geo.domain.GeoPoint;
import com.restspotfinder.geo.service.GeoService;
import com.restspotfinder.route.domain.TempRoute;
import com.restspotfinder.route.repository.TempRouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GeoServiceTest {
    @Autowired
    GeoService geoService;
    @Autowired
    TempRouteRepository tempRouteRepository;

    @Test
    void getGeoPoint() {
        // given
        String address = "충북 옥천군 옥천읍 중앙로 99";

        // when
        GeoPoint geoPoint = geoService.getGeoPoint(address);

        // then
        System.out.println("GeoPoint = " +  geoPoint);
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
        List<GeoPoint>  pointList = geoService.getRouteData(start, goal);

        // then
        for(GeoPoint p : pointList){
            System.out.println("p = " + p);
        }
    }
}