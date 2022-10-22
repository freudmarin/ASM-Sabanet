package com.marindulja.ASM.model;

import com.marindulja.ASM.model.clients.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "computer_product")
public class ComputerProduct extends BaseEntity<Long> {

    @NotBlank(message = "Serial number is mandatory")
    @Column(name="serial_number", nullable = false, unique = true)
    private String serialNumber;

    @NotBlank(message = "Problem description is mandatory")
    @Column(name = "problem_description", nullable = false)
    private String problemDescription;

    @NotBlank(message = "Brand is mandatory")
    @Column(nullable = false)
    private String brand;

    @Column(name = "purchase_date", nullable = false)
    private Date dateOfPurchase;

    @Column(name = "warranty_expire_date", nullable = false)
    private Date warrantyExpireDate;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Additional notes field is mandatory")
    @Column(name = "additional_notes", nullable = false)
    private String additionalNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComputerProduct that = (ComputerProduct) o;
        return Objects.equals(getSerialNumber(), that.getSerialNumber());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

