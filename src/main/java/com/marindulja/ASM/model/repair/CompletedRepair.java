package com.marindulja.ASM.model.repair;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("completed")
public class CompletedRepair extends Repair {

    @NotBlank(message = "Intervention is mandatory")
    @Column()
    private String intervention;
}
