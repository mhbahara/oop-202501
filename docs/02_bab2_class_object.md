# Bab 2 – Class dan Object (Produk Pertanian)

## Tujuan Pembelajaran
- Mahasiswa mampu **menjelaskan konsep class, object, atribut, dan method** dalam OOP.  
- Mahasiswa mampu **menerapkan access modifier dan enkapsulasi** dalam pembuatan class.  
- Mahasiswa mampu **mengimplementasikan class Produk pertanian** dengan atribut dan method yang sesuai.  
- Mahasiswa mampu **mendemonstrasikan instansiasi object** serta menampilkan data produk pertanian di console.  
- Mahasiswa mampu **menyusun laporan praktikum** dengan bukti kode, hasil eksekusi, dan analisis sederhana.  

---

## Ringkasan Teori
Class adalah blueprint atau cetak biru dari sebuah objek. Objek merupakan instansiasi dari class yang berisi atribut (data) dan method (perilaku). Dalam OOP, enkapsulasi dilakukan dengan menyembunyikan data menggunakan access modifier (public, private, protected) serta menyediakan akses melalui getter dan setter.  

Dalam konteks Agri-POS, produk pertanian seperti benih, pupuk, dan alat pertanian dapat direpresentasikan sebagai objek yang memiliki atribut nama, harga, dan stok. Dengan menggunakan class, setiap produk dapat dibuat, dikelola, dan dimanipulasi secara lebih terstruktur.  

---

## Langkah Praktikum
1. **Membuat Class Produk**
   - Buat file `Produk.java` pada package `model`.
   - Tambahkan atribut: `kode`, `nama`, `harga`, dan `stok`.
   - Gunakan enkapsulasi dengan menjadikan atribut bersifat private dan membuat getter serta setter untuk masing-masing atribut.  

2. **Membuat Class CreditBy**
   - Buat file `CreditBy.java` pada package `util`.
   - Isi class dengan method statis untuk menampilkan identitas mahasiswa di akhir output: `credit by: <NIM> - <Nama>`.

3. **Membuat Objek Produk dan Menampilkan Credit**
   - Buat file `MainProduk.java`.
   - Instansiasi minimal tiga objek produk, misalnya "Benih Padi", "Pupuk Urea", dan satu produk alat pertanian.
   - Tampilkan informasi produk melalui method getter.  
   - Panggil `CreditBy.print("<NIM>", "<Nama>")` di akhir `main` untuk menampilkan identitas.

4. **Commit dan Push**
   - Commit dengan pesan: `week2-class-object`.  

---

## Struktur Repositori
```
oop-20251-<nim>/
 └─ praktikum/week2-class-object/
     ├─ src/main/java/com/upb/agripos/model/
     │   └─ Produk.java
     ├─ src/main/java/com/upb/agripos/util/
     │   └─ CreditBy.java
     ├─ src/main/java/com/upb/agripos/
     │   └─ MainProduk.java
     ├─ screenshots/
     │   └─ hasil.png
     └─ laporan_week2.md
```

---

## Contoh Implementasi Program

### 1. Produk.java
```java
package com.upb.agripos.model;

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

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
}
```

### 2. CreditBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```

### 3. MainProduk.java
```java
package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        // Tampilkan identitas mahasiswa
        CreditBy.print("<NIM>", "<Nama Mahasiswa>");
    }
}
```

---

## Tugas dan Latihan
- **Tugas 1**: Buat class `Produk` dengan atribut kode, nama, harga, dan stok, serta method getter dan setter.
- **Tugas 2**: Buat class `CreditBy` pada package `util` yang menampilkan identitas mahasiswa dengan format **`credit by: <NIM> - <Nama>`**.
- **Tugas 3**: Instansiasi minimal tiga produk pertanian dan tampilkan informasinya di console, diakhiri dengan pemanggilan `CreditBy.print()`.
- **Latihan Mandiri**: Tambahkan method `tambahStok(int jumlah)` dan `kurangiStok(int jumlah)` untuk mengelola stok produk.  

**Ketentuan Pengumpulan**:  
- Sertakan kode program.  
- Sertakan screenshot hasil eksekusi program.  
- Commit log sesuai instruksi.  
- Laporan singkat (`laporan_week2.md`).  

**Contoh cuplikan output yang benar:**
```
Kode: BNH-001, Nama: Benih Padi IR64, Harga: 25000.0, Stok: 100
Kode: PPK-101, Nama: Pupuk Urea 50kg, Harga: 350000.0, Stok: 40
Kode: ALT-501, Nama: Cangkul Baja, Harga: 90000.0, Stok: 15

credit by: 2310112345 - Andi Pratama
```

---

## Checklist Keberhasilan
- [ ] Class `Produk` berhasil dibuat dengan atribut dan method yang lengkap.  
- [ ] Class `CreditBy` berhasil dibuat dan dipanggil di program utama.  
- [ ] Objek produk berhasil diinstansiasi dan ditampilkan.  
- [ ] Enkapsulasi sudah diterapkan dengan benar.  
- [ ] Commit dengan pesan sesuai instruksi berhasil dilakukan.  
- [ ] Screenshot hasil eksekusi telah dilampirkan.  
- [ ] Laporan singkat telah dibuat.  

---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?  
   **Jawaban:** …  

2. Apa fungsi getter dan setter dalam enkapsulasi?  
   **Jawaban:** …  

3. Bagaimana cara class `Produk` mendukung pengembangan aplikasi POS yang lebih kompleks?  
   **Jawaban:** …  

---

## Referensi
- Liang, Y. D. *Introduction to Java Programming* (Bab 9).  
- Schildt, H. *Java: The Complete Reference*.  
- Oracle Docs: [Classes and Objects](https://docs.oracle.com/javase/tutorial/java/concepts/class.html).  
