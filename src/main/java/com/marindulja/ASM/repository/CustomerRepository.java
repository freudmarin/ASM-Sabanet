package com.marindulja.ASM.repository;

import com.marindulja.ASM.model.clients.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
