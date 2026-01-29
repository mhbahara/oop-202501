package com.upb.agripos.model;

public class Receipt {
    private Transaction transaction;
    private String kasirName;

    public Receipt(Transaction transaction, String kasirName) {
        this.transaction = transaction;
        this.kasirName = kasirName;
    }

    public Transaction getTransaction() { return transaction; }
    public String getKasirName() { return kasirName; }
}
