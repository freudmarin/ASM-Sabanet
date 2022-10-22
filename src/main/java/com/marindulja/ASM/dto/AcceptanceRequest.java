package com.marindulja.ASM.dto;

import com.marindulja.ASM.model.ComputerProduct;
import com.marindulja.ASM.model.clients.Company;
import com.marindulja.ASM.model.clients.Customer;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class AcceptanceRequest {

    private ComputerProduct  product;
    private Company company;
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
