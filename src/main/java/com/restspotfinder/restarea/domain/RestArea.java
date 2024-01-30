package com.restspotfinder.restarea.domain;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.util.Map;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restAreaId;
    private String name; // 휴게소 이름
    private String code; // 휴게소 코드
    private String stdRestCd; // 휴게소/주유소코드
    private String routeNo; // 노선 코드
    private String routeName; // 노선 이름 ex) 경부고속도로
    private String serviceAreaCode;
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point position;

    public static RestArea fromPublic(Map<String, String> publicData) {
        double xValue = Double.parseDouble(publicData.get("xValue"));
        double yValue = Double.parseDouble(publicData.get("yValue"));
        return RestArea.builder()
                .name(publicData.get("unitName"))
                .code(publicData.get("unitCode"))
                .stdRestCd(publicData.get("stdRestCd"))
                .routeNo(publicData.get("routeNo"))
                .routeName(publicData.get("routeName"))
                .serviceAreaCode(publicData.get("serviceAreaCode"))
                .position(getPoint(xValue, yValue))
                .build();
    }

    private static Point getPoint(double xValue, double yValue) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(xValue, yValue));
    }
}
