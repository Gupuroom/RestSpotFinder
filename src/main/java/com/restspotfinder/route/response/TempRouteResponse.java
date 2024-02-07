package com.restspotfinder.route.response;

import com.restspotfinder.route.domain.OptionCode;
import com.restspotfinder.route.domain.TempRoute;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Coordinate;

import java.time.LocalDateTime;


@Getter
@Builder
public class TempRouteResponse {
    private Long routeId;
    private Coordinate[] coordinateList;
    private OptionCode optionCode;
    private LocalDateTime createdDate;

    public static TempRouteResponse from(TempRoute tempRoute) {
        return TempRouteResponse.builder()
                .routeId(tempRoute.getRouteId())
                .coordinateList(tempRoute.getLineString().getCoordinates())
                .optionCode(tempRoute.getOptionCode())
                .createdDate(tempRoute.getCreatedDate())
                .build();
    }
}