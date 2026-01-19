

   import model.AlatPertanian;
   import model.Benih;
   import model.Produk;
   import model.Pupuk;
   import util.CreditBy;

   public class MainPolymorphism {
       public static void main(String[] args) {
           Produk[] daftarProduk = new Produk[]{
               new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
               new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
               new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
           };

           for (Produk p : daftarProduk) {
            String info = p.getInfo();
            System.out.println(p.getInfo());  // Dynamic binding 
           }

           CreditBy.print("Bunga Maura Aulya", "240320562");
       }
   }
   