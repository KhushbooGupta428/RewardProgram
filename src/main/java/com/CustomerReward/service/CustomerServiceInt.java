package com.CustomerReward.service;

import java.time.LocalDate;
import java.util.Map;

 /* Service interface for customer reward operations*/

public interface CustomerServiceInt {

    int calculatePoints(double amount);

    Map<String, Integer> calculateMonthlyPoints(Long customerId, LocalDate startDate, LocalDate endDate);
}
