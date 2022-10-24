package com.marindulja.ASM.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto extends ClientDto {

    @NotBlank(message = "Customer name is mandatory")
    private String name;

    @NotBlank(message = "Customer surname is mandatory")
    private String surname;
}
