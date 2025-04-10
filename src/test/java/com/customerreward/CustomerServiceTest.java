package com.customerreward;

import com.customerreward.model.Transaction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.customerreward.repository.TransactionRepository;
import org.junit.runner.RunWith;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.customerreward.service.CustomerService;
import java.util.Collections;

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
    public void testCalculatePoints_Positive() {
        assertEquals(90, customerService.calculatePoints(120));
        assertEquals(50, customerService.calculatePoints(100));
        assertEquals(0, customerService.calculatePoints(40));
    }

    @Test
    public void testCalculatePoints_Negative() {
        assertNotEquals(100, customerService.calculatePoints(120));
        assertNotEquals(60, customerService.calculatePoints(100));
        assertNotEquals(10, customerService.calculatePoints(40));
        assertNotEquals(150, customerService.calculatePoints(124));
        assertNotEquals(200, customerService.calculatePoints(180));
        assertNotEquals(90, customerService.calculatePoints(130));

    }
    @Test
    public void testCalculateMonthlyPoints_Positive() {
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
        assertEquals(250, pointsMap.get("February").intValue());
        assertEquals(450, pointsMap.get("March").intValue());
    }
    @Test
    public void testCalculateMonthlyPoints_Negative() {
        List<Transaction> transactions = Collections.emptyList();

        Mockito.when(transactionRepository.findByCustomerIdAndDateBetween(1L, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 3, 31)))
                .thenReturn(transactions);

        Map<String, Integer> pointsMap = customerService.calculateMonthlyPoints(1L, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 3, 31));

        assertEquals(0, pointsMap.size());
    }

}
