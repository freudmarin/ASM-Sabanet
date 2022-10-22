package com.marindulja.ASM.service;

import com.marindulja.ASM.dto.AcceptanceRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface AcceptanceService {
    void acceptProduct(AcceptanceRequest acceptanceRequest) throws MethodArgumentNotValidException;
}
