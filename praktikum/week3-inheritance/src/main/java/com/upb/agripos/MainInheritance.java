

import model.AlatPertanian;
import model.Benih;
import model.Pupuk;
import util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001","Benih Padi IR64", 25000, 75, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 35, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 25, "Baja");
        
        b.deskripsi();
        p.deskripsi();
        a.deskripsi();

        CreditBy.print("Bunga Maura Aulya", "240320562");
    }
}