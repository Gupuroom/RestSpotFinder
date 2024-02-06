package com.restspotfinder.restarea.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restspotfinder.restarea.domain.RestArea;
import com.restspotfinder.restarea.repository.RestAreaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RestAreaServiceTest {
    @Autowired
    RestAreaService restAreaService;
    @Autowired
    RestAreaRepository restAreaRepository;
    
    @Test
    void findAll() throws JsonProcessingException {
        // given

        // when
        List<RestArea> restAreaList = restAreaRepository.findAll();

        // then
        ObjectMapper objectMapper = new ObjectMapper();
        for(RestArea r : restAreaList){
            System.out.println("restArea = " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r));
        }
    }

    @Test
    void getOneById() {
        // given
        long restAreaId = 1;
        
        // when
        RestArea restArea = restAreaService.getOneById(1);

        // then
        System.out.println("restArea = " + restArea);
    }
    
    @Test
    void getNearbyRoutes() {
        // given
        
        // when
        List<RestArea> restAreaList = restAreaService.getNearbyRoutes(6);
        
        // then
        for(RestArea r : restAreaList){
            System.out.println("r = " + r.getName() + " routeName : " + r.getRouteName());
        }
    }
}