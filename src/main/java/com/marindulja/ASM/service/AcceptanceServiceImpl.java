package com.marindulja.ASM.service;

import com.marindulja.ASM.dto.AcceptanceRequest;
import com.marindulja.ASM.model.ComputerProduct;
import com.marindulja.ASM.repository.CompanyRepository;
import com.marindulja.ASM.repository.ComputerRepository;
import com.marindulja.ASM.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AcceptanceServiceImpl implements AcceptanceService {

    private final CompanyRepository companyRepository;

    private final CustomerRepository customerRepository;
    private final ComputerRepository computerRepository;

    public AcceptanceServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository, ComputerRepository computerRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.computerRepository = computerRepository;
    }

    @Transactional
    @Override
    public void acceptProduct(AcceptanceRequest acceptanceRequest) {
        ComputerProduct computerProduct = acceptanceRequest.getProduct();

        if (acceptanceRequest.getCompany() != null) {
            companyRepository.save(acceptanceRequest.getCompany());
            computerProduct.setClient(acceptanceRequest.getCompany());
        } else {
            customerRepository.save(acceptanceRequest.getCustomer());
            computerProduct.setClient(acceptanceRequest.getCustomer());
        }
        computerRepository.save(acceptanceRequest.getProduct());
    }
}
