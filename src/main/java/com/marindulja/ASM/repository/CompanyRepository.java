package com.marindulja.ASM.repository;

import com.marindulja.ASM.model.clients.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
