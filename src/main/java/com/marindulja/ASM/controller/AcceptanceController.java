package com.marindulja.ASM.controller;

import com.marindulja.ASM.dto.AcceptanceRequest;
import com.marindulja.ASM.service.AcceptanceService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acceptance")
@Slf4j
public class AcceptanceController {

    @Autowired
    private AcceptanceService acceptanceService;


    @PostMapping("/acceptNewProduct")
    public ResponseEntity acceptProduct(@RequestBody @Valid AcceptanceRequest acceptanceRequest) {
        try {
            acceptanceService.acceptProduct(acceptanceRequest);
        } catch (MethodArgumentNotValidException e ){
            log.warn("Validation errors: " + e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
