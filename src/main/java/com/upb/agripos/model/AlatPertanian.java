package main.java.com.upb.agripos.model;

 public class AlatPertanian extends Produk {
       private String material;

       // Constructor
       public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
           super(kode, nama, harga, stok);
           this.material = material;
       }

       // Getter
       public String getMaterial() {
           return material;
       }

       // Setter
       public void setMaterial(String material) {
           this.material = material;
       }

       // Override toString
       @Override
       public String toString() {
           return super.toString() + ", Material: " + material;
       }
   }
   
