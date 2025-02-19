package org.example.service;

import org.example.model.Balance;
import org.example.model.CashOperation;
import org.example.model.Denomination;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperationService {
    private double totalBgn = 1000;
    private double totalEur = 2000;
    private Map<Double, Integer> denominations;

    public OperationService() {
        denominations = new HashMap<>();
        denominations.put(50.0, 10);
        denominations.put(10.0, 50);
        denominations.put(100.0, 10);
        denominations.put(20.0, 50);
    }

    public Balance processCashOperation(CashOperation operation) {
        if (operation.getOperationType().equalsIgnoreCase("withdrawal")) {
            withdraw(operation);
        } else if (operation.getOperationType().equalsIgnoreCase("deposit")) {
            deposit(operation);
        }
        updateTransactionHistory(operation);
        updateBalanceFile();
        return getCurrentBalance();
    }

    private void withdraw(CashOperation operation) {
        if (operation.getCurrency().equals("BGN")) {
            if (operation.getAmount() > totalBgn) {
                throw new IllegalArgumentException("Insufficient BGN balance.");
            }
            totalBgn -= operation.getAmount();
        } else if (operation.getCurrency().equals("EUR")) {
            if (operation.getAmount() > totalEur) {
                throw new IllegalArgumentException("Insufficient EUR balance.");
            }
            totalEur -= operation.getAmount();
        }
        updateDenominations(operation.getDenominations(), false);
    }

    private void deposit(CashOperation operation) {
        if (operation.getCurrency().equals("BGN")) {
            totalBgn += operation.getAmount();
        } else if (operation.getCurrency().equals("EUR")) {
            totalEur += operation.getAmount();
        }
        updateDenominations(operation.getDenominations(), true);
    }

    private void updateDenominations(List<Denomination> denominationsList, boolean isDeposit) {
        for (Denomination denomination : denominationsList) {
            double value = denomination.getValue();
            int quantity = denomination.getQuantity();

            if (isDeposit) {
                denominations.put(value, denominations.getOrDefault(value, 0) + quantity);
            } else {
                int currentQuantity = denominations.getOrDefault(value, 0);
                if (currentQuantity < quantity) {
                    throw new IllegalArgumentException("Insufficient denominations for withdrawal.");
                }
                denominations.put(value, currentQuantity - quantity);
            }
        }
    }

    public Balance getCurrentBalance() {
        Balance balance = new Balance();
        balance.setTotalBgn(totalBgn);
        balance.setTotalEur(totalEur);
        balance.setDenominations(denominations);
        return balance;
    }

    private void updateTransactionHistory(CashOperation operation) {
        String transactionDetails = String.format("%s: %s %s - Amount: %.2f",
                operation.getOperationType(),
                operation.getCurrency(),
                operation.getDenominations().toString(),
                operation.getAmount());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_history.txt", true))) {
            writer.write(transactionDetails);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBalanceFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("balance.txt"))) {
            writer.write("Total BGN: " + totalBgn);
            writer.newLine();
            writer.write("Total EUR: " + totalEur);
            writer.newLine();

            for (Map.Entry<Double, Integer> entry : denominations.entrySet()) {
                writer.write("Denomination " + entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
