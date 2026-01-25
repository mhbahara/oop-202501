// ========== TransactionService.java ==========
package com.upb.agripos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.dao.TransactionDAOImpl;
import com.upb.agripos.exception.OutOfStockException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.TransactionDetail;
import com.upb.agripos.util.SessionManager;

public class TransactionService {
    private TransactionDAO transactionDAO;
    private ProductDAO productDAO;
    private SessionManager sessionManager;
    
    public TransactionService() {
        this.transactionDAO = new TransactionDAOImpl();
        this.productDAO = new ProductDAOImpl();
        this.sessionManager = SessionManager.getInstance();
    }
    
    public Transaction createTransaction(List<CartItem> cartItems, 
                                        String paymentMethod,
                                        double paymentAmount,
                                        double changeAmount) throws Exception {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new ValidationException("Keranjang kosong");
        }
        
        // Validate stock untuk semua items
        for (CartItem item : cartItems) {
            Product product = productDAO.findByKode(item.getProduct().getKode());
            if (product.getStok() < item.getQuantity()) {
                throw new OutOfStockException(product.getNama(), 
                    product.getStok(), item.getQuantity());
            }
        }
        
        // Calculate total
        double subtotal = cartItems.stream()
            .mapToDouble(CartItem::getSubtotal)
            .sum();
        
        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(generateTransactionCode());
        transaction.setUserId(sessionManager.getCurrentUserId());
        transaction.setSubtotal(subtotal);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setPaymentAmount(paymentAmount);
        transaction.setChangeAmount(changeAmount);
        transaction.setStatus("COMPLETED");
        
        // Save transaction
        Integer transactionId = transactionDAO.saveTransaction(transaction);
        transaction.setId(transactionId);
        
        // Save transaction details and update stock
        for (CartItem item : cartItems) {
            TransactionDetail detail = new TransactionDetail(
                item.getProduct(), 
                item.getQuantity()
            );
            detail.setTransactionId(transactionId);
            transactionDAO.saveTransactionDetail(detail);
            
            // Update stock
            Product product = item.getProduct();
            int newStock = product.getStok() - item.getQuantity();
            productDAO.updateStock(product.getKode(), newStock);
        }
        
        return transaction;
    }
    
    public Transaction getTransactionById(Integer id) throws Exception {
        Transaction transaction = transactionDAO.findById(id);
        if (transaction != null) {
            List<TransactionDetail> details = transactionDAO.findDetailsByTransactionId(id);
            transaction.setDetails(details);
        }
        return transaction;
    }
    
    public Transaction getTransactionByCode(String code) throws Exception {
        Transaction transaction = transactionDAO.findByCode(code);
        if (transaction != null) {
            List<TransactionDetail> details = transactionDAO.findDetailsByTransactionId(transaction.getId());
            transaction.setDetails(details);
        }
        return transaction;
    }
    
    public List<Transaction> getAllTransactions() throws Exception {
        return transactionDAO.findAll();
    }
    
    public List<Transaction> getTransactionsByDate(LocalDate date) throws Exception {
        return transactionDAO.findByDate(date);
    }
    
    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) 
            throws Exception {
        return transactionDAO.findByDateRange(startDate, endDate);
    }
    
    private String generateTransactionCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return "TRX-" + LocalDateTime.now().format(formatter);
    }
}
