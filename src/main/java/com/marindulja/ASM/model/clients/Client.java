package com.marindulja.ASM.model.clients;

import com.marindulja.ASM.model.BaseEntity;
import com.marindulja.ASM.model.ComputerProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "client")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="client_type",
        discriminatorType = DiscriminatorType.STRING)
public class Client extends BaseEntity<Long> {
    @NotBlank(message = "Address is mandatory")
    @Column(name = "full_address", nullable = false)
    private String fullAddress;

    @NotBlank(message = "Telephone number is mandatory")
    @Column(name = "telephone_number", nullable = false)
    private String telephone;

    @Column(name = "email")
    private String email;

    @NotBlank(message = "Fiscal code is mandatory")
    @Column(name = "fiscal_code", nullable = false)
    private String fiscalCode;

    @NotBlank(message = "PEC code is mandatory")
    @Column(name = "pec", nullable = false)
    private String pec;

    @Column(name = "client_type", nullable = false, insertable = false, updatable = false)
    private String clientType;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<ComputerProduct> productsOfCustomers;
}
