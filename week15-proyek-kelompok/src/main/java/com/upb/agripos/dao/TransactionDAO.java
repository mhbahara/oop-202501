// ========== TransactionDAO.java ==========
package com.upb.agripos.dao;

import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.TransactionDetail;
import java.time.LocalDate;
import java.util.List;

public interface TransactionDAO {
    Integer saveTransaction(Transaction transaction) throws Exception;
    void saveTransactionDetail(TransactionDetail detail) throws Exception;
    Transaction findById(Integer id) throws Exception;
    Transaction findByCode(String code) throws Exception;
    List<Transaction> findAll() throws Exception;
    List<Transaction> findByDate(LocalDate date) throws Exception;
    List<Transaction> findByDateRange(LocalDate startDate, LocalDate endDate) throws Exception;
    List<TransactionDetail> findDetailsByTransactionId(Integer transactionId) throws Exception;
}