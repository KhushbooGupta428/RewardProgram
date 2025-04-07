package com.CustomerReward.repository;

import com.CustomerReward.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Repository interface for link Customer entities*/
 @Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}


