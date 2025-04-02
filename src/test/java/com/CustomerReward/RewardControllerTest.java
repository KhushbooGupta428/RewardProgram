package com.CustomerReward;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the RewardController class.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testGetMonthlyPoints() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/rewards/monthly/1?startDate=2025-01-01&endDate=2025-03-31", Map.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }


}
