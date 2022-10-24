package com.marindulja.ASM.model.repair;


import com.marindulja.ASM.model.BaseEntity;
import com.marindulja.ASM.model.users.Technician;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repair")
public class Repair extends BaseEntity<Long> {

    @NotBlank(message = "Repair Status is mandatory")
    @Column(name = "repair_status")
    private String repairStatus;

    @NotBlank(message = "Amount to be charged is mandatory")
    @Column(name = "amount_to_be_charged")
    private double amountToBeCharged;


    @NotBlank(message = "Refusal reason is mandatory")
    @Column(name = "refusal_reason")
    private String refusalReason;


    @NotBlank(message = "Intervention is mandatory")
    @Column()
    private String intervention;
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
