package com.upb.agripos;

import com.upb.agripos.model.kontrak.Receiptable;
import com.upb.agripos.model.pembayaran.Cash;
import com.upb.agripos.model.pembayaran.EWallet;
import com.upb.agripos.model.pembayaran.Pembayaran;
import com.upb.agripos.model.pembayaran.TransferBank;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran[] daftarPembayaran = new Pembayaran[] {
            new Cash("INV001", 100000, 120000),
            new EWallet("INV002", 200000, "rafi@ewallet", true),
            new TransferBank("INV003", 300000, "BCA", false)
        };

        for (Pembayaran p : daftarPembayaran) {
            System.out.println(p.getClass().getSimpleName());
            if (p instanceof Receiptable r) {
                System.out.println(r.cetakStruk());
            } else {
                System.out.println("Struk tidak tersedia.");
            }
            System.out.println("-----------------------------");
        }

        CreditBy.print("240202878", "Rafi Kurniawan");
    }
}
