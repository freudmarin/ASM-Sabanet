package com.marindulja.ASM.model.repair;


import com.marindulja.ASM.model.BaseEntity;
import com.marindulja.ASM.model.users.Technician;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repair")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="repair_type",
        discriminatorType = DiscriminatorType.STRING)
public class Repair extends BaseEntity<Long> {

    @Column(name = "repair_type", nullable = false, insertable = false, updatable = false)
    private String repairType;

    @NotBlank(message = "Amount to be charged is mandatory")
    @Column(name = "amount_to_be_charged")
    private double amountToBeCharged;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id", nullable = false)
    private Technician technician;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Repair repair = (Repair) o;
        return getId() != null && Objects.equals(getId(), repair.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
