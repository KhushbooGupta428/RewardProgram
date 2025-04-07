package com.CustomerReward;

import com.CustomerReward.model.Transaction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.CustomerReward.repository.*;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.CustomerReward.service.*;

/**
 * Unit tests for the Controller class.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {


    @InjectMocks
    private CustomerService customerService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void testCalculatePoints() {
        assertEquals(90, customerService.calculatePoints(120.0));
        assertEquals(25, customerService.calculatePoints(75.0));
        assertEquals(0, customerService.calculatePoints(50.0));
    }

    @Test
    public void testCalculateMonthlyPoints() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, 1L, 120.0, LocalDate.of(2025, 1, 15)),
                new Transaction(2L, 1L, 75.0, LocalDate.of(2025, 1, 20)),
                new Transaction(3L, 1L, 200.0, LocalDate.of(2025, 2, 10)),
                new Transaction(4L, 1L, 50.0, LocalDate.of(2025, 2, 25)),
                new Transaction(5L, 1L, 300.0, LocalDate.of(2025, 3, 5))
        );

        Mockito.when(transactionRepository.findByCustomerIdAndDateBetween(1L, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 3, 31)))
                .thenReturn(transactions);

        Map<String, Integer> pointsMap = customerService.calculateMonthlyPoints(1L, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 3, 31));

        assertEquals(3, pointsMap.size());
        assertEquals(115, pointsMap.get("January").intValue());
        assertEquals(200, pointsMap.get("February").intValue());
        assertEquals(450, pointsMap.get("March").intValue());
    }
}
