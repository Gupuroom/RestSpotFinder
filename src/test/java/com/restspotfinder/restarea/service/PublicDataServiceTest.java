package com.restspotfinder.restarea.service;

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
class PublicDataServiceTest {
    @Autowired
    PublicDataService publicDataService;

    @Test
    void getRestAreaData() {
        // given

        // when
        List<RestArea> restAreaList =  publicDataService.getRestAreaData();

        // then
    }
}