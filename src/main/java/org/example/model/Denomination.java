package org.example.model;

public class Denomination {
    private String type;    // Тип на банкнотата (например "BGN_10")
    private int quantity;    // Количество на банкнотите
    private double value;    // Стойността на деноминацията

    public Denomination() {}

    public Denomination(String type, int quantity, double value) {
        this.type = type;
        this.quantity = quantity;
        this.value = value;  // Инициализиране на стойността
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

    public double getValue() { // Методът getValue() трябва да е дефиниран
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
