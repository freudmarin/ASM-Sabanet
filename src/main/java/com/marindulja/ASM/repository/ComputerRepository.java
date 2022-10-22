package com.marindulja.ASM.repository;

import com.marindulja.ASM.model.ComputerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository  extends JpaRepository<ComputerProduct, Long> {
}
