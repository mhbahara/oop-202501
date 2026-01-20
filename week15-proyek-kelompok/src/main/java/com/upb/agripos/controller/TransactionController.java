package com.upb.agripos.controller;

import com.upb.agripos.exception.InsufficientPaymentException;
import com.upb.agripos.model.*;
import com.upb.agripos.service.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TransactionController {
    private TransactionService transactionService;
    private PaymentService paymentService;
    private ReceiptService receiptService;
    private ReportService reportService;
    
    public TransactionController() {
        this.transactionService = new TransactionService();
        this.paymentService = new PaymentService();
        this.receiptService = new ReceiptService();
        this.reportService = new ReportService();
    }
    
    public Transaction checkout(List<CartItem> cartItems, 
                               String paymentMethod,
                               double paymentAmount) throws Exception {
        // Process payment
        double totalAmount = cartItems.stream()
            .mapToDouble(CartItem::getSubtotal)
            .sum();
        
        double changeAmount = paymentService.processPayment(
            paymentMethod, totalAmount, paymentAmount);
        
        // Create transaction
        Transaction transaction = transactionService.createTransaction(
            cartItems, paymentMethod, paymentAmount, changeAmount);
        
        return transaction;
    }
    
    public Receipt generateReceipt(Transaction transaction) {
        return receiptService.generateReceipt(transaction);
    }
    
    public String getReceiptText(Receipt receipt) {
        return receiptService.formatReceiptText(receipt);
    }
    
    public Transaction getTransactionById(Integer id) throws Exception {
        return transactionService.getTransactionById(id);
    }
    
    public List<Transaction> getAllTransactions() throws Exception {
        return transactionService.getAllTransactions();
    }
    
    public Map<String, Object> getDailySalesReport(LocalDate date) throws Exception {
        return reportService.getDailySalesReport(date);
    }
    
    public Map<String, Object> getSalesReportByDateRange(LocalDate startDate, LocalDate endDate) 
            throws Exception {
        return reportService.getSalesReportByDateRange(startDate, endDate);
    }
}