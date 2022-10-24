package com.marindulja.ASM.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto extends ClientDto {

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @NotBlank(message = "VAT_number is mandatory")
    private String vatNumber;
}
