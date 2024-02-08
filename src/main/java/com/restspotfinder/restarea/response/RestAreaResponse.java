package com.restspotfinder.restarea.response;

import com.restspotfinder.restarea.domain.RestArea;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;


@Getter
@Builder
public class RestAreaResponse {
    private Long restAreaId;
    private String name; // "휴게소명"
    private String routeName; // "도로노선명"
    private String routeDirection; // "도로노선방향"
    private Coordinate coordinate;

    public static RestAreaResponse from(RestArea restArea) {
        return RestAreaResponse.builder()
                .restAreaId(restArea.getRestAreaId())
                .name(restArea.getName())
                .routeName(restArea.getRouteName())
                .routeDirection(restArea.getRouteDirection())
                .coordinate(restArea.getPosition().getCoordinate())
                .build();
    }

    public static List<RestAreaResponse> fromList(List<RestArea> restAreaList){
        return restAreaList.stream().map(RestAreaResponse::from).toList();
    }
}
