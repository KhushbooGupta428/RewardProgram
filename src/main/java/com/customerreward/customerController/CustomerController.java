package com.customerreward.customerController;

import com.customerreward.dto.CustomerReward;
import com.customerreward.dto.RewardPoints;
import com.customerreward.exception.CustomerNotFoundException;
import com.customerreward.repository.CustomerRepository;
import com.customerreward.service.CustomerServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.customerreward.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
  Controller for handling reward-related requests.
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerServiceInt customerService;

    @Autowired
    private  CustomerRepository customerRepository;

    @GetMapping("/rewards")
    public ResponseEntity<List<CustomerReward>> getMonthlyPoints(
            @RequestParam(required = false) Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<CustomerReward> customerRewards;

        if (customerId != null) {
            // Fetch reward points for a specific customer
            if (!customerRepository.existsById(customerId)) {
                throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
            }
            customerRewards = List.of(calculateCustomerReward(customerId, start, end));
        } else {
            // Fetch reward points for all customers
            List<Customer> customers = customerRepository.findAll();
            customerRewards = customers.stream()
                    .map(customer -> calculateCustomerReward(customer.getId(), start, end))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(customerRewards);
    }

    private CustomerReward calculateCustomerReward(Long customerId, LocalDate start, LocalDate end) {
        Map<String, Integer> pointsMap = customerService.calculateMonthlyPoints(customerId, start, end);

        List<RewardPoints> rewardPointsList = pointsMap.entrySet().stream()
                .map(entry -> new RewardPoints(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        int totalPoints = pointsMap.values().stream().mapToInt(Integer::intValue).sum();
        return new CustomerReward(customerId, rewardPointsList, totalPoints);
    }
}


