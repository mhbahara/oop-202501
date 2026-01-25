// ========== ReceiptService.java ==========
package com.upb.agripos.service;

import com.upb.agripos.model.Receipt;
import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.TransactionDetail;
import com.upb.agripos.util.SessionManager;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class ReceiptService {
    private SessionManager sessionManager;
    private NumberFormat currencyFormat;
    
    public ReceiptService() {
        this.sessionManager = SessionManager.getInstance();
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }
    
    public Receipt generateReceipt(Transaction transaction) {
        String kasirName = sessionManager.getCurrentUserName();
        return new Receipt(transaction, kasirName);
    }
    
    public String formatReceiptText(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        Transaction trx = receipt.getTransaction();
        
        sb.append("=".repeat(50)).append("\n");
        sb.append("             AGRI-POS - TOKO PERTANIAN\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append(String.format("No. Transaksi : %s\n", trx.getTransactionCode()));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        sb.append(String.format("Tanggal       : %s\n", 
            trx.getTransactionDate().format(formatter)));
        sb.append(String.format("Kasir         : %s\n", receipt.getKasirName()));
        sb.append("-".repeat(50)).append("\n");
        
        sb.append(String.format("%-20s %5s %12s %12s\n", 
            "Produk", "Qty", "Harga", "Subtotal"));
        sb.append("-".repeat(50)).append("\n");
        
        for (TransactionDetail detail : trx.getDetails()) {
            sb.append(String.format("%-20s %5d %12s %12s\n",
                detail.getProductNama().length() > 20 ? 
                    detail.getProductNama().substring(0, 17) + "..." : 
                    detail.getProductNama(),
                detail.getQuantity(),
                currencyFormat.format(detail.getPrice()),
                currencyFormat.format(detail.getSubtotal())
            ));
        }
        
        sb.append("-".repeat(50)).append("\n");
        sb.append(String.format("%-38s %12s\n", "TOTAL:", 
            currencyFormat.format(trx.getSubtotal())));
        sb.append(String.format("%-38s %12s\n", "Metode Bayar:", 
            trx.getPaymentMethod()));
        sb.append(String.format("%-38s %12s\n", "Dibayar:", 
            currencyFormat.format(trx.getPaymentAmount())));
        sb.append(String.format("%-38s %12s\n", "Kembali:", 
            currencyFormat.format(trx.getChangeAmount())));
        sb.append("=".repeat(50)).append("\n");
        sb.append("           TERIMA KASIH ATAS KUNJUNGAN ANDA\n");
        sb.append("=".repeat(50)).append("\n");
        
        return sb.toString();
    }
}
