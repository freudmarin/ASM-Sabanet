package com.marindulja.ASM.model;

import jakarta.persistence.*;
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
@Table(name = "repair_issue")

public class RepairIssue extends BaseEntity<Long> {

    @Column(name = "case_number", nullable = false)
    private String caseNumber;

    @OneToOne()
    @JoinColumn(name = "computer_id")
    private ComputerProduct computerProduct;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RepairIssue that = (RepairIssue) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
