package com.restspotfinder.restarea.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.restspotfinder.restarea.domain.RestArea;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PublicDataService {
    public List<RestArea> getRestAreaData() {
        ClassPathResource jsonFile = new ClassPathResource("/RestArea.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<RestArea> restAreaList = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonFile.getInputStream());
            ArrayNode recordsNode = (ArrayNode) rootNode.get("records");

            for (JsonNode node : recordsNode) {
                RestArea restArea = RestArea.from(node);
                restAreaList.add(restArea);
                System.out.println("restArea = " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(restArea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restAreaList;
    }
}
