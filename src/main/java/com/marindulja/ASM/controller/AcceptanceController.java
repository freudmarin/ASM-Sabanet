package com.marindulja.ASM.controller;

import com.marindulja.ASM.dto.AcceptanceRequestDto;
import com.marindulja.ASM.service.AcceptanceService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acceptance")
@Slf4j
public class AcceptanceController {
    private final AcceptanceService acceptanceService;

    public AcceptanceController(AcceptanceService acceptanceService) {
        this.acceptanceService = acceptanceService;
    }

    @PostMapping("/acceptNewProduct")
    public ResponseEntity acceptProduct(@RequestBody @Valid AcceptanceRequestDto acceptanceRequestDto) {
        acceptanceService.acceptProduct(acceptanceRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
