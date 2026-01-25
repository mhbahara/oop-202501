// ========== ReportService.java ==========
package com.upb.agripos.service;

import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.dao.TransactionDAOImpl;
import com.upb.agripos.model.Transaction;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private TransactionDAO transactionDAO;
    
    public ReportService() {
        this.transactionDAO = new TransactionDAOImpl();
    }
    
    public Map<String, Object> getDailySalesReport(LocalDate date) throws Exception {
        List<Transaction> transactions = transactionDAO.findByDate(date);
        return generateSalesReport(transactions);
    }
    
    public Map<String, Object> getSalesReportByDateRange(LocalDate startDate, LocalDate endDate) 
            throws Exception {
        List<Transaction> transactions = transactionDAO.findByDateRange(startDate, endDate);
        return generateSalesReport(transactions);
    }
    
    private Map<String, Object> generateSalesReport(List<Transaction> transactions) {
        Map<String, Object> report = new HashMap<>();
        
        int totalTransactions = transactions.size();
        double totalRevenue = transactions.stream()
            .mapToDouble(Transaction::getSubtotal)
            .sum();
        
        long cashCount = transactions.stream()
            .filter(t -> "TUNAI".equals(t.getPaymentMethod()))
            .count();
        
        long ewalletCount = transactions.stream()
            .filter(t -> "E-WALLET".equals(t.getPaymentMethod()))
            .count();
        
        double cashRevenue = transactions.stream()
            .filter(t -> "TUNAI".equals(t.getPaymentMethod()))
            .mapToDouble(Transaction::getSubtotal)
            .sum();
        
        double ewalletRevenue = transactions.stream()
            .filter(t -> "E-WALLET".equals(t.getPaymentMethod()))
            .mapToDouble(Transaction::getSubtotal)
            .sum();
        
        report.put("totalTransactions", totalTransactions);
        report.put("totalRevenue", totalRevenue);
        report.put("cashCount", cashCount);
        report.put("ewalletCount", ewalletCount);
        report.put("cashRevenue", cashRevenue);
        report.put("ewalletRevenue", ewalletRevenue);
        report.put("transactions", transactions);
        
        return report;
    }
}
