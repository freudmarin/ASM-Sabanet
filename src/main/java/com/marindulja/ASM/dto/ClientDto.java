package com.marindulja.ASM.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    @NotBlank(message = "Address is mandatory")
    private String fullAddress;

    @NotBlank(message = "Telephone number is mandatory")
    private String telephone;

    @Column(name = "email")
    private String email;

    @NotBlank(message = "Fiscal code is mandatory")
    private String fiscalCode;

    @NotBlank(message = "PEC code is mandatory")
    private String pec;

    private String clientType;

}
