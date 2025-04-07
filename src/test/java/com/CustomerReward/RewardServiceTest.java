package com.CustomerReward;

import com.CustomerReward.service.CustomerService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RewardService class
 */
public class RewardServiceTest {

    private final CustomerService rewardService = new CustomerService();

    @Test
    public void testCalculatePoints_Positive() {
        // Test case where points are calculated correctly for various amounts
        assertEquals(90, rewardService.calculatePoints(120));
        assertEquals(50, rewardService.calculatePoints(100));
        assertEquals(0, rewardService.calculatePoints(40));
        assertEquals(150, rewardService.calculatePoints(200));
        assertEquals(75, rewardService.calculatePoints(150));
    }

    @Test
    public void testCalculatePoints_Negative() {
        // Test case where points calculation might be incorrect or edge cases
        assertNotEquals(100, rewardService.calculatePoints(120));
        assertNotEquals(60, rewardService.calculatePoints(100));
        assertNotEquals(10, rewardService.calculatePoints(40));
        assertNotEquals(150, rewardService.calculatePoints(124));
        assertNotEquals(200, rewardService.calculatePoints(180));
        assertNotEquals(90, rewardService.calculatePoints(130));


    }


}
