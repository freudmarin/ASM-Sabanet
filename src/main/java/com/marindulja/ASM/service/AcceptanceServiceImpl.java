package com.marindulja.ASM.service;

import com.marindulja.ASM.dto.AcceptanceRequestDto;
import com.marindulja.ASM.dto.CompanyDto;
import com.marindulja.ASM.dto.ComputerProductDto;
import com.marindulja.ASM.dto.CustomerDto;
import com.marindulja.ASM.model.ComputerProduct;
import com.marindulja.ASM.model.RepairIssue;
import com.marindulja.ASM.model.clients.Company;
import com.marindulja.ASM.model.clients.Customer;
import com.marindulja.ASM.repository.CompanyRepository;
import com.marindulja.ASM.repository.ComputerRepository;
import com.marindulja.ASM.repository.CustomerRepository;
import com.marindulja.ASM.repository.RepairIssueRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AcceptanceServiceImpl implements AcceptanceService {

    private final CompanyRepository companyRepository;

    private final CustomerRepository customerRepository;
    private final ComputerRepository computerRepository;

    private final RepairIssueRepository repairIssueRepository;

    private ModelMapper mapper = new ModelMapper();

    public AcceptanceServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
                                 ComputerRepository computerRepository,
                                 RepairIssueRepository repairIssueRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.computerRepository = computerRepository;
        this.repairIssueRepository = repairIssueRepository;
    }

    @Transactional
    @Override
    public void acceptProduct(AcceptanceRequestDto acceptanceRequest) {
        ComputerProductDto computerProduct = acceptanceRequest.getProduct();
        ComputerProduct computerProductEntity = mapToEntity(computerProduct);

        if (acceptanceRequest.getCompany() != null) {
            Company company = mapToEntity(acceptanceRequest.getCompany());
            companyRepository.save(company);
            computerProductEntity.setClient(company);
        } else {
            Customer customer = mapToEntity(acceptanceRequest.getCustomer());
            customerRepository.save(customer);
            computerProductEntity.setClient(customer);
        }
        computerRepository.save(computerProductEntity);

        RepairIssue repairIssue = new RepairIssue();
        repairIssue.setComputerProduct(computerProductEntity);
        repairIssue.setCaseNumber(UUID.randomUUID().toString());
        repairIssueRepository.save(repairIssue);
    }

    private ComputerProductDto mapToDTO(ComputerProduct computerProduct) {
        ComputerProductDto computerProductDto = mapper.map(computerProduct, ComputerProductDto.class);
        return computerProductDto;
    }

    private ComputerProduct mapToEntity(ComputerProductDto computerProductDto) {
        ComputerProduct computerProduct = mapper.map(computerProductDto, ComputerProduct.class);
        return computerProduct;
    }

    private CompanyDto mapToDTO(Company company) {
        CompanyDto companyDto = mapper.map(company, CompanyDto.class);
        return companyDto;
    }

    private Company mapToEntity(CompanyDto companyDto) {
        Company company = mapper.map(companyDto, Company.class);
        company.setClientType("company");
        return company;
    }

    private CustomerDto mapToDTO(Customer customer) {
        CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);
        customer.setClientType("customer");
        return customer;
    }
}
