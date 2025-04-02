package com.CustomerReward.customerController;


import com.CustomerReward.dto.CustomerReward;
import com.CustomerReward.dto.RewardPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CustomerReward.service.CustomerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for handling reward-related requests.
 */
@RestController
@RequestMapping("/rewards")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Endpoint to get reward points for a customer in a specific month.
     */
    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<CustomerReward> getMonthlyPoints(
            @PathVariable Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        Map<String, Integer> pointsMap = customerService.calculateMonthlyPoints(customerId, start, end);

        List<RewardPoints> rewardPointsList = pointsMap.entrySet().stream()
                .map(entry -> new RewardPoints(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        int totalPoints = pointsMap.values().stream().mapToInt(Integer::intValue).sum(); // Calculate total points
        CustomerReward customerReward = new CustomerReward(customerId, rewardPointsList, totalPoints); // Include total points

        return ResponseEntity.ok(customerReward);
    }
}
