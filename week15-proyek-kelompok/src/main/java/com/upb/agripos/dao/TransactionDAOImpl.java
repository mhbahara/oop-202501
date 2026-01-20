// ========== TransactionDAOImpl.java ==========
package com.upb.agripos.dao;

import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.TransactionDetail;
import com.upb.agripos.util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    private Connection getConnection() {
        return DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Integer saveTransaction(Transaction transaction) throws Exception {
        String sql = "INSERT INTO transactions (transaction_code, user_id, subtotal, payment_method, payment_amount, change_amount, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, transaction.getTransactionCode());
            stmt.setInt(2, transaction.getUserId());
            stmt.setDouble(3, transaction.getSubtotal());
            stmt.setString(4, transaction.getPaymentMethod());
            stmt.setDouble(5, transaction.getPaymentAmount());
            stmt.setDouble(6, transaction.getChangeAmount());
            stmt.setString(7, transaction.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
        throw new Exception("Failed to save transaction");
    }

    @Override
    public void saveTransactionDetail(TransactionDetail detail) throws Exception {
        String sql = "INSERT INTO transaction_details (transaction_id, product_id, product_kode, product_nama, quantity, price, subtotal) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, detail.getTransactionId());
            stmt.setInt(2, detail.getProductId());
            stmt.setString(3, detail.getProductKode());
            stmt.setString(4, detail.getProductNama());
            stmt.setInt(5, detail.getQuantity());
            stmt.setDouble(6, detail.getPrice());
            stmt.setDouble(7, detail.getSubtotal());
            stmt.executeUpdate();
        }
    }

    @Override
    public Transaction findById(Integer id) throws Exception {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTransaction(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Transaction findByCode(String code) throws Exception {
        String sql = "SELECT * FROM transactions WHERE transaction_code = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTransaction(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY transaction_date DESC";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByDate(LocalDate date) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE DATE(transaction_date) = ? ORDER BY transaction_date DESC";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapResultSetToTransaction(rs));
                }
            }
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByDateRange(LocalDate startDate, LocalDate endDate) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE DATE(transaction_date) BETWEEN ? AND ? ORDER BY transaction_date DESC";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapResultSetToTransaction(rs));
                }
            }
        }
        return transactions;
    }

    @Override
    public List<TransactionDetail> findDetailsByTransactionId(Integer transactionId) throws Exception {
        List<TransactionDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM transaction_details WHERE transaction_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, transactionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    details.add(mapResultSetToTransactionDetail(rs));
                }
            }
        }
        return details;
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setTransactionCode(rs.getString("transaction_code"));
        transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime());
        transaction.setUserId(rs.getInt("user_id"));
        transaction.setSubtotal(rs.getDouble("subtotal"));
        transaction.setPaymentMethod(rs.getString("payment_method"));
        transaction.setPaymentAmount(rs.getDouble("payment_amount"));
        transaction.setChangeAmount(rs.getDouble("change_amount"));
        transaction.setStatus(rs.getString("status"));
        return transaction;
    }

    private TransactionDetail mapResultSetToTransactionDetail(ResultSet rs) throws SQLException {
        TransactionDetail detail = new TransactionDetail();
        detail.setId(rs.getInt("id"));
        detail.setTransactionId(rs.getInt("transaction_id"));
        detail.setProductId(rs.getInt("product_id"));
        detail.setProductKode(rs.getString("product_kode"));
        detail.setProductNama(rs.getString("product_nama"));
        detail.setQuantity(rs.getInt("quantity"));
        detail.setPrice(rs.getDouble("price"));
        detail.setSubtotal(rs.getDouble("subtotal"));
        return detail;
    }
}