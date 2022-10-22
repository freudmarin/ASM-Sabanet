package com.marindulja.ASM.model.clients;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("company")
public class Company  extends Client {
    @NotBlank(message = "Company name is mandatory")
    @Column(name = "company_name")
    private String companyName;

    @NotBlank(message = "VAT_number is mandatory")
    @Column(name = "VAT_number")
    private String vatNumber;
}
