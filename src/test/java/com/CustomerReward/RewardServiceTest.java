package com.CustomerReward;

import com.CustomerReward.service.CustomerService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RewardService class.
 */
public class RewardServiceTest {

    private final CustomerService rewardService = new CustomerService();

    @Test
    public void testCalculatePoints_Positive() {
        // Test case where points are calculated correctly for various amounts
        assertEquals(90, rewardService.calculatePoints(120)); // Amount above threshold
        assertEquals(50, rewardService.calculatePoints(100)); // Amount exactly at threshold
        assertEquals(0, rewardService.calculatePoints(40));  // Amount below threshold
    }

    @Test
    public void testCalculatePoints_Negative() {
        // Test case where points calculation might be incorrect or edge cases
        assertNotEquals(100, rewardService.calculatePoints(120)); // Incorrect expected points
        assertNotEquals(60, rewardService.calculatePoints(100));  // Incorrect expected points
        assertNotEquals(10, rewardService.calculatePoints(40));   // Incorrect expected points
    }


}
