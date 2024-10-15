package org.example.controller;

import org.example.model.Balance;
import org.example.model.CashOperation;
import org.example.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cash-operation")
public class OperationController {

    @Autowired
    private OperationService cashOperationService;

    @PostMapping
    public ResponseEntity<?> handleCashOperation(
            @Valid @RequestBody CashOperation operation,
            @RequestHeader("FIB-X-AUTH") String apiKey) {

        if (!isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }

        Balance updatedBalance = cashOperationService.processCashOperation(operation);
        return ResponseEntity.ok(updatedBalance);
    }

    private boolean isValidApiKey(String apiKey) {
        return apiKey.equals("f9Uie8nNf112hx8s");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body("Validation error: " + errorMessage);
    }
}
