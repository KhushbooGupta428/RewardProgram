package com.customerreward.service;

import com.customerreward.model.Transaction;
import com.customerreward.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Service for calculating reward points based on transactions
 */
@Service
public class CustomerService  implements CustomerServiceInt{

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Calculates reward points based on the transaction amount.
     */

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    public Map<String, Integer> calculateMonthlyPoints(Long customerId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndDateBetween(customerId, startDate, endDate);
        Map<String, Integer> monthlyPoints = new HashMap<>();
        for (Transaction transaction : transactions) {
            String month = transaction.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int points = calculatePoints(transaction.getAmount());
            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
        }
        return monthlyPoints;
    }
}
