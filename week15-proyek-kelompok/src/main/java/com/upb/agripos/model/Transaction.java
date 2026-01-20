package com.upb.agripos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private Integer id;
    private String transactionCode;
    private LocalDateTime transactionDate;
    private Integer userId;
    private Double subtotal;
    private String paymentMethod;
    private Double paymentAmount;
    private Double changeAmount;
    private String status;
    private List<TransactionDetail> details;

    public Transaction() {
        this.details = new ArrayList<>();
        this.transactionDate = LocalDateTime.now();
        this.status = "COMPLETED";
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getTransactionCode() { return transactionCode; }
    public void setTransactionCode(String transactionCode) { 
        this.transactionCode = transactionCode; 
    }
    
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { 
        this.transactionDate = transactionDate; 
    }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { 
        this.paymentMethod = paymentMethod; 
    }
    
    public Double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(Double paymentAmount) { 
        this.paymentAmount = paymentAmount; 
    }
    
    public Double getChangeAmount() { return changeAmount; }
    public void setChangeAmount(Double changeAmount) { 
        this.changeAmount = changeAmount; 
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public List<TransactionDetail> getDetails() { return details; }
    public void setDetails(List<TransactionDetail> details) { 
        this.details = details; 
    }
}
