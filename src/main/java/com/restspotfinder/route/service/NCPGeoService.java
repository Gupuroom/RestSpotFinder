package com.restspotfinder.route.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.restspotfinder.route.domain.OptionCode;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class NCPGeoService {
    @Value("${ncp.geo.url}")
    String POINT_URL;
    @Value("${ncp.geo.direct5-url}")
    String ROUTE_URL;
    @Value("${ncp.geo.client-id}")
    String CLIENT_ID;
    @Value("${ncp.geo.client-secret}")
    String CLIENT_SECRET;

    public Coordinate getCoordinate(String address) {
        String requestURL = POINT_URL + "?query=" + address;

        JsonNode jsonNode = connect(requestURL);
        JsonNode addressesNode = jsonNode.get("addresses").get(0);
        System.out.println("roadAddress = " + addressesNode.get("roadAddress"));
        System.out.println("jibunAddress = " + addressesNode.get("jibunAddress"));

        double x = addressesNode.get("x").asDouble();
        double y = addressesNode.get("y").asDouble();
        return new Coordinate(x, y);
    }

    public Map<OptionCode, Coordinate[]> getRouteData(String start, String goal) {
        List<String> optionList = List.of(OptionCode.optimal.getValue(), OptionCode.fast.getValue(), OptionCode.avoidtoll.getValue());
        String requestURL = ROUTE_URL + "?start=" + start + "&goal=" + goal + "&option=" + String.join(":", optionList);
        JsonNode jsonNode = connect(requestURL);

        // 경로 데이터 추출
        Coordinate[] fastRoute = extractRoute(jsonNode, OptionCode.fast);
        Coordinate[] optimalRoute = extractRoute(jsonNode, OptionCode.optimal);
        Coordinate[] avoidtollRoute = extractRoute(jsonNode, OptionCode.avoidtoll);

        return Map.of(
                OptionCode.fast, fastRoute,
                OptionCode.optimal, optimalRoute,
                OptionCode.avoidtoll, avoidtollRoute);
    }

    private Coordinate[] extractRoute(JsonNode jsonNode, OptionCode optionCode) {
        ArrayNode pathNode = (ArrayNode) jsonNode.get("route").get(optionCode.getValue()).get(0).get("path");
        List<Coordinate> route = new ArrayList<>();
        for (JsonNode path : pathNode) {
            double x = path.get(0).asDouble();
            double y = path.get(1).asDouble();
            route.add(new Coordinate(x, y));
        }
        return route.toArray(new Coordinate[0]);
    }

    public JsonNode connect(String requestURL) {
        try {
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
            headers.set("X-NCP-APIGW-API-KEY", CLIENT_SECRET);

            HttpEntity<Object> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, entity, String.class);

            return new ObjectMapper().readTree(responseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
