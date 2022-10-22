package com.marindulja.ASM.model.users;

import com.marindulja.ASM.model.RepairIssue;
import com.marindulja.ASM.model.repair.Repair;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("technician")
public class Technician extends User {

    @OneToMany(mappedBy = "technician", fetch = FetchType.LAZY)
    private List<RepairIssue> repairIssues;

    @OneToMany(mappedBy = "technician", fetch = FetchType.LAZY)
    private List<Repair> repairs;

}
