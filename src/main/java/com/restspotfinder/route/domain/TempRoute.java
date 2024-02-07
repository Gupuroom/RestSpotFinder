package com.restspotfinder.route.domain;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;
    @Column(columnDefinition = "geometry(LineString, 4326)")
    private LineString lineString;
    @Enumerated(EnumType.STRING)
    private OptionCode optionCode;
    private LocalDateTime createdDate;

    public static TempRoute from(GeometryFactory geometryFactory, Coordinate[] coordinates, OptionCode optionCode) {
        return TempRoute.builder()
                .lineString(geometryFactory.createLineString(coordinates))
                .optionCode(optionCode)
                .createdDate(LocalDateTime.now())
                .build();
    }
}