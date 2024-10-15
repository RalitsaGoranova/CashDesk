package org.example.model;

public class Denomination {
    private String type;
    private int quantity;
    private double value;

    public Denomination() {}

    public Denomination(String type, int quantity, double value) {
        this.type = type;
        this.quantity = quantity;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
