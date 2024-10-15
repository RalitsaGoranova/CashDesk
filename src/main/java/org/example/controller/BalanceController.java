package org.example.controller;


import org.example.model.Balance;
import org.example.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cash-balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;
    @Value("${api.key}")
    private String apiKey;

    @GetMapping
    public ResponseEntity<Balance> getBalance(@RequestHeader("FIB-X-AUTH") String apiKey) {
        if (!isValidApiKey(apiKey)) {
            return ResponseEntity.status(403).body(null);
        }

        Balance balance = balanceService.getCurrentBalance();
        return ResponseEntity.ok(balance);
    }

    private boolean isValidApiKey(String apiKeyProv) {
        return apiKey.equals(apiKeyProv);
    }
}