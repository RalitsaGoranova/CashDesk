package org.example.model;

import java.util.Map;

public class Balance {
    private double totalBgn;
    private double totalEur;
    private Map<Double, Integer> denominations;

    public Balance() {}

    public double getTotalBgn() {
        return totalBgn;
    }

    public void setTotalBgn(double totalBgn) {
        this.totalBgn = totalBgn;
    }

    public double getTotalEur() {
        return totalEur;
    }

    public void setTotalEur(double totalEur) {
        this.totalEur = totalEur;
    }

    public Map<Double, Integer> getDenominations() {
        return denominations;
    }

    public void setDenominations(Map<Double, Integer> denominations) {
        this.denominations = denominations;
    }
}