package com.marindulja.ASM.dto;

import com.marindulja.ASM.model.ComputerProduct;
import com.marindulja.ASM.model.clients.Company;
import com.marindulja.ASM.model.clients.Customer;
import jakarta.validation.Valid;

public class AcceptanceRequest {

    @Valid
    private ComputerProduct  product;
    @Valid
    private Company company;
    @Valid
    private Customer customer;
    public ComputerProduct getProduct() {
        return product;
    }

    public Company getCompany() {
        return company;
    }

    public Customer getCustomer(){
        return customer;
    }
}
