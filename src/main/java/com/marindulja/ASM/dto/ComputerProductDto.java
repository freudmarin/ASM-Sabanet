package com.marindulja.ASM.dto;

import com.marindulja.ASM.model.RepairIssue;
import com.marindulja.ASM.model.clients.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ComputerProductDto {

    @NotBlank(message = "Serial number is mandatory")
    private String serialNumber;

    @NotBlank(message = "Problem description is mandatory")
    private String problemDescription;

    @Column(nullable = false)
    private String brand;

    private Date dateOfPurchase;

    private Date warrantyExpireDate;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Additional notes field is mandatory")
    private String additionalNotes;


    private ClientDto client;

    private RepairIssue repairIssue;
}
