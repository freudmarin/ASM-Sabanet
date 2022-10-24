package com.marindulja.ASM.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptanceRequestDto {

    @Valid
    private ComputerProductDto  product;
    @Valid
    private CompanyDto company;
    @Valid
    private CustomerDto customer;
    public ComputerProductDto getProduct() {
        return product;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public CustomerDto getCustomer(){
        return customer;
    }
}
