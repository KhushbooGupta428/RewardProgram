package com.CustomerReward.repository;

import com.CustomerReward.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/* Repository interface for accessing transaction data*/

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

   List <Transaction> findByCustomerIdAndDateBetween(Long customerId, LocalDate start, LocalDate end);
}
