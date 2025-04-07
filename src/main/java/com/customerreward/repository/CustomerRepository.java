package com.customerreward.repository;

import com.customerreward.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Repository interface for link Customer entities..*/
 @Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}


