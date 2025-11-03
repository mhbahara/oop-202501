package com.upb.agripos;

import com.upb.agripos.pembayaran.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        // Polimorfisme: Menggunakan Pembayaran sebagai tipe referensi
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");
        Pembayaran tb = new TransferBank("INV-003", 200000, "1234567890", "1234");

        // Tampilkan struk menggunakan interface Receiptable (polimorfik)
        System.out.println("=== Struk Pembayaran ===");
        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) tb).cetakStruk());

        // Credit
        CreditBy.print("240320565", "Wisnu Wibowo S"); 
    }
}
