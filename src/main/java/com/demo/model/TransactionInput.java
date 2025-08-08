package com.demo.model;

public class TransactionInput {
    private double amount;
    private String country;
    private double hour;
    private String cardType;

    public TransactionInput() {}

    public TransactionInput(double amount, String country, double hour, String cardType) {
        this.amount = amount;
        this.country = country;
        this.hour = hour;
        this.cardType = cardType;
    }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getHour() { return hour; }
    public void setHour(double hour) { this.hour = hour; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
}