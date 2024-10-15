package org.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CashOperation {
    @NotNull
    private String operationType;

    @NotNull
    @Min(1)
    private Double amount;

    @NotNull
    private String currency;

    @NotNull
    private List<Denomination> denominations;

    public CashOperation() {}

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Denomination> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Denomination> denominations) {
        this.denominations = denominations;
    }
}