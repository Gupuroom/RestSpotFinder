package com.restspotfinder.route.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;
    private Long routeId;
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point position;
    private LocalDateTime createdDate;

    public static TempPoint from(GeometryFactory geometryFactory, Coordinate coordinate, long routeId) {
        return TempPoint.builder()
                .routeId(routeId)
                .position(geometryFactory.createPoint(coordinate))
                .createdDate(LocalDateTime.now())
                .build();
    }

    public static List<TempPoint> fromList(GeometryFactory geometryFactory, Coordinate[] coordinates, long routeId) {
        return Arrays.stream(coordinates).map(coordinate -> TempPoint.from(geometryFactory, coordinate, routeId)).toList();
    }
}
