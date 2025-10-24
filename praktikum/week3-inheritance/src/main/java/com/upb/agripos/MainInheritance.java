package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        System.out.println("=== SISTEM PRODUK AGRI-POS ===\n");

        Produk benih = new Benih("Benih Padi IR64", 25000, "Padi", 120);
        Produk pupuk = new Pupuk("Pupuk NPK Mutiara", 150000, "Nitrogen, Fosfor, Kalium", 25);
        Produk alat = new AlatPertanian("Cangkul Baja", 85000, "Baja Karbon", "Medium");

        benih.tampilkanInfo();
        pupuk.tampilkanInfo();
        alat.tampilkanInfo();

        CreditBy.tampilkanCredit();
    }
}
