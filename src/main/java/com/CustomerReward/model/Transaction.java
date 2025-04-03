package com.CustomerReward.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing a transaction made by a customer.
 */
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Double amount;
    private LocalDate date;
}
