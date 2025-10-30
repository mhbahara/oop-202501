# Laporan Praktikum Minggu 4 
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : [Wisnu Wibowo Saputro]
- NIM   : [240320565]
- Kelas : [3DSRA]

---

## Tujuan

Mahasiswa mampu menjelaskan konsep polymorphism dalam OOP, membedakan method overloading dan overriding, mengimplementasikan polymorphism (overriding, overloading, dynamic binding) dalam program, serta menganalisis contoh kasus polymorphism pada sistem nyata (Agri-POS).

---

## Dasar Teori

1. Polymorphism berarti "banyak bentuk" dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.
2. Overloading adalah mendefinisikan method dengan nama sama tetapi parameter berbeda dalam class yang sama.
3. Overriding adalah subclass mengganti implementasi method dari superclass dengan nama dan parameter yang sama.
4. Dynamic Binding adalah pemanggilan method ditentukan saat runtime berdasarkan tipe objek aktual, bukan tipe referensi.
5. Dalam Agri-POS, polymorphism memungkinkan method seperti getInfo() di-override oleh subclass (Benih, Pupuk, dll.) untuk menampilkan detail spesifik, meningkatkan fleksibilitas sistem.

---

## Langkah Praktikum

1. Setup Project: Buat struktur repositori sesuai panduan (folder praktikum/week4-polymorphism/ dengan subfolder src/main/java/com/upb/agripos/model/, dll.).
2. Implementasi Overloading: Tambahkan method tambahStok(int jumlah) dan tambahStok(double jumlah) pada class Produk.java.
3. Implementasi Overriding: Tambahkan method getInfo() pada superclass Produk.java, lalu override di subclass Benih.java, Pupuk.java, AlatPertanian.java, dan ObatHama.java (latihan mandiri).
4. Implementasi Dynamic Binding: Buat array Produk[] daftarProduk di MainPolymorphism.java yang berisi objek subclass, lalu loop untuk memanggil getInfo().
5. Main Class: Buat MainPolymorphism.java untuk mendemonstrasikan overloading, overriding, dan dynamic binding, serta panggil CreditBy.print().
6. Latihan Mandiri: Tambahkan subclass ObatHama.java yang mengoverride getInfo().
7. Testing dan Run: Compile dan jalankan program untuk memverifikasi output.
8. Commit: Commit dengan pesan "week4-polymorphism" dan push ke repositori.

---

## Kode Program

// MainPolymorphism.java (Kode utama untuk demo)
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.*;
import main.java.com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        System.out.println("=== Demo Polymorphism dalam Agri-POS ===\n");

        // Demo Overloading: Panggil tambahStok dengan parameter berbeda
        System.out.println("--- Demo Overloading: tambahStok ---");
        Produk produk = new Produk("PRD-001", "Produk Umum", 10000, 50);
        System.out.println("Stok awal: " + produk.getStok());
        produk.tambahStok(10);  // Overload dengan int
        System.out.println("Stok setelah tambah int (10): " + produk.getStok());
        produk.tambahStok(5.5);  // Overload dengan double
        System.out.println("Stok setelah tambah double (5.5): " + produk.getStok() + "\n");

        // Dynamic Binding: Array Produk[] dengan objek subclass
        System.out.println("--- Demo Dynamic Binding: Daftar Produk ---");
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
            new ObatHama("OBH-201", "Pestisida Serangga", 150000, 20, "Serangga")  // Latihan mandiri
        };

        for (int i = 0; i < daftarProduk.length; i++) {
            System.out.println((i + 1) + ". " + daftarProduk[i].getInfo());
        }

        System.out.println("\n--- Credit ---");
        CreditBy.print("240320565", "Wisnu Wibowo Saputro");
    }
}

// Produk.java (Overloading & getInfo default)
package main.java.com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Overloading: Method tambahStok dengan parameter int
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    // Overloading: Method tambahStok dengan parameter double
    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    // Method getInfo() yang akan di-override oleh subclass
    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }

    // Getter untuk akses atribut
    public int getStok() {
        return stok;
    }
}

// Benih.java (Overriding)
package main.java.com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }
}

// Pupuk.java (Overriding)
package main.java.com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", Jenis: " + jenis;
    }
}

// AlatPertanian.java (Overriding)
package main.java.com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String bahan;

    public AlatPertanian(String kode, String nama, double harga, int stok, String bahan) {
        super(kode, nama, harga, stok);
        this.bahan = bahan;
    }

    @Override
    public String getInfo() {
        return "Alat Pertanian: " + super.getInfo() + ", Bahan: " + bahan;
    }
}

// ObatHama.java (Latihan Mandiri: Overriding)
package main.java.com.upb.agripos.model;

public class ObatHama extends Produk {
    private String jenisHama;

    public ObatHama(String kode, String nama, double harga, int stok, String jenisHama) {
        super(kode, nama, harga, stok);
        this.jenisHama = jenisHama;
    }

    @Override
    public String getInfo() {
        return "Obat Hama: " + super.getInfo() + ", Jenis Hama: " + jenisHama;
    }
}

// CreditBy.java
package main.java.com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("Dikerjakan oleh: " + nama + " (" + nim + ")");
    }
} 
---

## Hasil Eksekusi

![alt text](<image week 4.png>)
---

## Analisis

1. Cara Kode Berjalan: Program dimulai dengan demo overloading di Produk, di mana method     tambahStok dipanggil dengan parameter berbeda. Kemudian, array Produk[] berisi objek subclass, dan tabel menampilkan data menggunakan getter dan instanceof untuk spesifik. Dynamic binding memastikan method getInfo() dipanggil sesuai subclass.
2. Perbedaan dengan Minggu Sebelumnya: Minggu sebelumnya fokus pada inheritance dasar; minggu ini menekankan polymorphism untuk behavior dinamis, dengan output tabel yang lebih terstruktur.
3. Kendala yang Dihadapi dan Cara Mengatasinya: Error kompilasi pada getter atau package diperbaiki dengan menambahkan getter dan memastikan struktur folder benar. Jika output tidak rapi, gunakan printf untuk alignment.

---

## Kesimpulan

Praktikum ini berhasil mendemonstrasikan polymorphism melalui overloading, overriding, dan dynamic binding dalam sistem Agri-POS. Output tabel membuat data lebih mudah dibaca, dan implementasi ini meningkatkan fleksibilitas kode.
---

## Quiz

1. Apa perbedaan overloading dan overriding?
   Jawaban: Overloading adalah teknik di mana method dengan nama yang sama didefinisikan dalam class yang sama, tetapi dengan parameter yang berbeda (jumlah atau tipe). Ini ditentukan pada compile time. Overriding adalah teknik di mana subclass mengganti implementasi method dari superclass dengan nama dan parameter yang sama, dan ini ditentukan pada runtime melalui dynamic binding.  

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?
   Jawaban: Java menentukan method mana yang dipanggil berdasarkan tipe objek aktual (instance) saat runtime, bukan berdasarkan tipe referensi variabel. Ini memungkinkan polymorphism di mana method yang di-override dipilih sesuai subclass objek yang sebenarnya.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.
   Jawaban: Dalam sistem POS retail umum, method calculateDiscount() bisa di-override oleh subclass seperti ElectronicProduct (diskon 10% untuk elektronik), Clothing (diskon 20% untuk pakaian), dan Food (diskon 5% untuk makanan). Saat array Product[] dipanggil, diskon dihitung sesuai jenis produk aktual, memungkinkan fleksibilitas dalam penanganan berbagai kategori produk.
