package com.restspotfinder.geo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class GeoPoint {
    private String xValue; // 경도
    private String yValue; // 위도

    public static GeoPoint from(String xValue, String yValue){
        return GeoPoint.builder()
                .xValue(xValue)
                .yValue(yValue)
                .build();
    }

    public static LineString convertToLineString(List<GeoPoint> geoPointList){
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = geoPointList.stream()
                .map(geoPoint -> new Coordinate(
                        Double.parseDouble(geoPoint.getXValue()), // 경도
                        Double.parseDouble(geoPoint.getYValue())  // 위도
                ))
                .toArray(Coordinate[]::new);
        return geometryFactory.createLineString(coordinates);
    }
}