package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // ============================
        // HEADER
        // ============================
        System.out.println("=====================================");
        System.out.println("   PRAKTIKUM INHERITANCE - WEEK 3   ");
        System.out.println("=====================================");

        // ============================
        // INSTANSIASI OBJEK
        // ============================
        Benih benih = new Benih("B001", "Benih Padi", 50000, 100, "IR64");
        Pupuk pupuk = new Pupuk("P001", "Pupuk Organik", 75000, 50, "NPK");
        AlatPertanian alat = new AlatPertanian("A001", "Cangkul", 100000, 20, "Baja");

        // ============================
        // TAMPILKAN DATA PRODUK DALAM TABEL
        // ============================
        System.out.println("\n--- DATA PRODUK ---");
        System.out.printf("%-10s %-15s %-10s %-8s %-15s\n", "Kode", "Nama", "Harga", "Stok", "Spesifik");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-10.0f %-8d %-15s\n",
                benih.getKode(), benih.getNama(), benih.getHarga(), benih.getStok(), benih.getVarietas());
        System.out.printf("%-10s %-15s %-10.0f %-8d %-15s\n",
                pupuk.getKode(), pupuk.getNama(), pupuk.getHarga(), pupuk.getStok(), pupuk.getJenisPupuk());
        System.out.printf("%-10s %-15s %-10.0f %-8d %-15s\n",
                alat.getKode(), alat.getNama(), alat.getHarga(), alat.getStok(), alat.getMaterial());

        // ============================
        // CONTOH PENGGUNAAN METHOD INHERITANCE
        // ============================
        System.out.println("\n--- PENGGUNAAN METHOD INHERITANCE ---");
        benih.tambahStok(10);
        pupuk.kurangiStok(5);

        // ============================
        // CREDIT
        // ============================
        System.out.println("\n--- CREDIT ---");
        CreditBy.displayCredit("Wisnu Wibowo Saputro", "240320565", "3DSRA");

        // ============================
        // FOOTER
        // ============================
        System.out.println("=====================================");
        System.out.println("   SELESAI - TERIMA KASIH!   ");
        System.out.println("=====================================");
    }
}
