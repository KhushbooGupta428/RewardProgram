package com.CustomerReward;

import com.CustomerReward.model.Customer;
import com.CustomerReward.model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.CustomerReward.repository.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration tests for the RewardController class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setup() {
        Customer customer1 = new Customer(1L, "John Doe", "john.doe@example.com");
        Customer customer2 = new Customer(2L, "Jane Smith", "jane.smith@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer1, customer2);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(
                new Transaction(1L, 1L, 120.0, LocalDate.of(2025, 1, 15)),
                new Transaction(2L, 1L, 75.0, LocalDate.of(2025, 1, 20)),
                new Transaction(3L, 1L, 200.0, LocalDate.of(2025, 2, 10)),
                new Transaction(4L, 1L, 50.0, LocalDate.of(2025, 2, 25)),
                new Transaction(5L, 1L, 150.0, LocalDate.of(2025, 3, 5)),
                new Transaction(6L, 2L, 80.0, LocalDate.of(2025, 1, 18)),
                new Transaction(7L, 2L, 110.0, LocalDate.of(2025, 2, 12)),
                new Transaction(8L, 2L, 95.0, LocalDate.of(2025, 3, 22))
        );
    }

    @Test
    public void testGetMonthlyPointsForSpecificCustomer() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/rewards?customerId=1&startDate=2025-01-01&endDate=2025-03-31", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetMonthlyPointsForAllCustomers() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/rewards?startDate=2025-01-01&endDate=2025-03-31", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetMonthlyPointsCustomerNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/rewards?customerId=999&startDate=2025-01-01&endDate=2025-03-31", String.class);
        assertEquals(404, response.getStatusCode().value());
        assertTrue(response.getBody().contains("Customer with ID 999 not found."));
    }

    @Test
    public void testGetMonthlyPointsNoTransactions() {
        when(transactionRepository.findByCustomerIdAndDateBetween(1L, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 3, 31)))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List> response = restTemplate.getForEntity("/api/v1/rewards?customerId=1&startDate=2025-01-01&endDate=2025-03-31", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }


}
