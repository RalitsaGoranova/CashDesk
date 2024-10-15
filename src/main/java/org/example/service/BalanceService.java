package org.example.service;

import org.example.model.Balance;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final OperationService cashOperationService;

    public BalanceService(OperationService cashOperationService) {
        this.cashOperationService = cashOperationService;
    }
    public Balance getCurrentBalance() {
        return cashOperationService.getCurrentBalance();
    }
}