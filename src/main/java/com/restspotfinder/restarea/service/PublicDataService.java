package com.restspotfinder.restarea.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restspotfinder.restarea.domain.RestArea;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PublicDataService {

    public List<RestArea> getRestAreaData(int pageNo, int numOfRows) {
        String requestURL = "http://data.ex.co.kr/openapi/locationinfo/locationinfoRest?key=5770481058&type=json" + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo;
        JsonNode rootNode = connect(requestURL);

        List<RestArea> restAreaList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        if (rootNode.get("list") != null && rootNode.get("list").isArray()) {
            for (JsonNode restAreaNode : rootNode.get("list")) {
                Map publicData = objectMapper.convertValue(restAreaNode, Map.class);
                restAreaList.add(RestArea.fromPublic(publicData));
            }
        }

        return restAreaList;
    }

    public JsonNode connect(String requestURL) {
        try {
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
            HttpHeaders headers = new HttpHeaders();

            HttpEntity<Object> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, entity, String.class);

            return new ObjectMapper().readTree(responseEntity.getBody());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
